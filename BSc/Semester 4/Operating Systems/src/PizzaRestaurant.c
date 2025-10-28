//------------------------------------------------------Include.h-----------------------------------------------------------------------------------------------------------
#include "PizzaRestaurant.h"
//------------------------------------------------------Functions-------------------------------------------------------------------------------------------------------------
void destroyer(int thread_count) {
    //destroy every mutex and condition variable defined
    pthread_mutex_t* destroy_mutexes[] = {&pizza_maker_mutex, &ovens_mutex, &package_mutex, &delivery_mutex, &screen_mutex, &statics_mutex}; // list of mutex references
    pthread_cond_t* destroy_conds[] = {&pizza_maker_cond, &ovens_cond, &package_cond, &delivery_cond};// list of condition variables refeces
    for(int i = 0; i < thread_count; i++) {
        if(i <= 5){
            pthread_mutex_destroy(destroy_mutexes[i]);
        }
        else{
            pthread_cond_destroy(destroy_conds[i]);
        }
    }
    pthread_exit(NULL);
}
    
void check_rc(int rs, int thread_count) {
    if (rs != 0) {
        //if an error occured, the function prints a message using access_screen function and exits the program
        access_screen("An error was occurred. Please try to compile the programm again.\n", 0, 0, 0, 0);
        destroyer(thread_count);
        exit(-1);
    }
    return;
}

//locks the screen and allows one thread only to print a message, and maybe some arguments
void access_screen (void* arg, unsigned int arg1, unsigned int arg2, unsigned int arg3, unsigned int count) {
    char* str = (char*) arg;
    rc = pthread_mutex_lock(&screen_mutex); //lock the screen access and allow only one thread to use the screen
    check_rc(rc, 10);
    if(count == 0) { //count variable represents the number of the given arguments, except the string
        printf("%s", str);
    }
    else if(count == 1) {
        printf(str, arg1);
    }
    else if(count == 2) {
        printf(str, arg1, arg2);
    }
    else { 
        printf(str, arg1, arg2, arg3);
    }
    rc = pthread_mutex_unlock(&screen_mutex);
    check_rc(rc, 10);
}
//--------------------------------------------------------Order------------------------------------------------------------------------------------------------------------
void *order(void *args) {
    struct arguments *args2 = args; //we use a struct, as we pass more than 1 argument
    unsigned int id = *(args2 -> idp); //the variable id saves the first argument converting the pointer into an actual value (dereference)
    unsigned int seed = *(args2 -> seedp); //the variable seed saves the second argument converting the pointer into an actual value (dereference)
    unsigned int time_passed = *(args2 -> time); //the variable time_passed saves the third argument converting the pointer into an actual value (dereference)
    struct timespec customer_entity_packing, packing; //initialiting timespec variables in order to calculate time for each time space
    struct timespec customer_entity_delivery, delivery;
    struct timespec finish_baking, delivery_cooling;
    unsigned int preparation;
    unsigned int deliveyy;
    unsigned int cooling;
    unsigned int sample_seed = seed + id; //initialiting a different seed for each order by adding the id
    unsigned int pizzas_propability;
    unsigned int payment_time;
    unsigned int delivery_time;
    unsigned int card_status;
    unsigned int sample = 5;
    unsigned int plain = 0;
    unsigned int special = 0;

    rc = clock_gettime(CLOCK_REALTIME, &customer_entity_packing); //marks the begining of a specific time space
    check_rc(rc, 10);
    
    rc = clock_gettime(CLOCK_REALTIME, &customer_entity_delivery);
    check_rc(rc, 10);

    unsigned int how_many_pizzas = 1 + rand_r(&sample_seed) % sample; //generating a random number of pizzas, between 1 and 5
//---------------------------------------------------how_many_pizzas-------------------------------------------------------------------------------------------------------
    for(int i = 1; i <= how_many_pizzas; i++) { //count plain and special    
        pizzas_propability = 1 + rand_r(&sample_seed) % 100; //generating a random number to determine the outcome
        if(pizzas_propability > Pplain * 100) { //determining if a pizza is plain or special
            special++;
        }
        else {
            plain++;
        }
    }
//---------------------------------------------------------Card------------------------------------------------------------------------------------------------------------
    payment_time = Tpaymentlow + rand_r(&sample_seed) % Tpaymenthigh; //generating a random number, which represents the time costumer needs to wait in order to pay
    time_passed += payment_time;

    card_status = 1 + rand_r(&sample_seed) % 100; //generating a random number between 1 and 100, in order to determine if the payment failed or not
    sleep(payment_time);

    if(card_status > Pfail * 100) {
        access_screen("\nThe orded with id %u was given at time %u\n", id, time_passed, 0, 2);
        rc = pthread_mutex_lock(&statics_mutex); //lock the statistics_mutex
        check_rc(rc, 10);
        how_many_passed++;
        income += Cspecial * special + Cplain * plain; //increase the income according to the quantity of the ordered pizzas
        total_plain += plain;
        total_special += special;
        rc = pthread_mutex_unlock(&statics_mutex); //unlock the statistics_mutex
        check_rc(rc, 10);
    }
    else {
        access_screen("\nThe order with id %u was failed at time %u\n", id, time_passed, 0, 2);
        rc = pthread_mutex_lock(&statics_mutex);
        check_rc(rc, 10);
        how_many_failed++; //if the order fails to be payed we print a message and increase a counter of failurs, by 1
        rc = pthread_mutex_unlock(&statics_mutex);
        check_rc(rc, 10);
        pthread_exit(NULL);
    }
    access_screen("\nProcessing order %u containing %u plain and %u special πίτσες\n", id, plain, special, 3);
//------------------------------------------------------pizza_makers-------------------------------------------------------------------------------------------------------
    rc = pthread_mutex_lock(&pizza_maker_mutex);
    check_rc(rc, 10); //check if the lock is done correctly (in a similar way we check and the unlock)

    while (pizza_makers == 0) { //while all the pizza_makers are unavailable, all the other threads wait untill at least one pizza_maker become available
        access_screen("\nThe order %u didn't find an available maker. It is blocked...\n" , id, 0, 0, 1);
        rc = pthread_cond_wait(&pizza_maker_cond, &pizza_maker_mutex); //the threads wait, enters in hibranated mode
        check_rc(rc, 10);
    }

    pizza_makers--; //using one of the pizza makers 
    rc = pthread_mutex_unlock(&pizza_maker_mutex);
    check_rc(rc, 10);

    access_screen("\nThe order %u is being prepared.\n" , id, 0, 0, 1);

    sleep(Tprep * how_many_pizzas); //sleeping while the pizza maker is working
//--------------------------------------------------------Ovens------------------------------------------------------------------------------------------------------------
    rc = pthread_mutex_lock(&ovens_mutex);
    check_rc(rc, 10);

    //while the free ovens are not at least equals to the wanted pizzas, we make the other threads wait
    while (ovens < how_many_pizzas) {
        access_screen("\nThe order %u didn't find an available oven. It is blocked...\n" , id, 0, 0, 1);
        rc = pthread_cond_wait(&ovens_cond, &ovens_mutex);
        check_rc(rc, 10);
    }

    ovens -= how_many_pizzas; //bind the nessesary number of ovens
    rc = pthread_mutex_unlock(&ovens_mutex);
    check_rc(rc, 10);

    access_screen("\nThe order %u is being baked." , id, 0, 0, 1);

    rc = pthread_mutex_lock(&pizza_maker_mutex);
    check_rc(rc, 10);
    
    pizza_makers++; //seting a pizza makers free, because they put the pizzas in the ovens

    rc = pthread_cond_signal(&pizza_maker_cond); //wake up a number of threads for the pizza_makers
    check_rc(rc, 10);
    
    rc = pthread_mutex_unlock(&pizza_maker_mutex);
    check_rc(rc, 10);
    
    sleep(Tbake);

    rc = clock_gettime(CLOCK_REALTIME, &finish_baking);
    check_rc(rc, 10);
//-------------------------------------------------------Packers-----------------------------------------------------------------------------------------------------------
    rc = pthread_mutex_lock(&package_mutex);
    check_rc(rc, 10); //check if the lock is done correctly (in a similar way we check and the unlock)

    while (packers == 0) { //while all the pizza_packers are unavailable, all the other threads wait untill at least one pizza_packer become available
        access_screen( "\nThe order %u didn't find available packer. It is blocked...\n", id, 0, 0, 1);
        rc = pthread_cond_wait(&package_cond, &package_mutex);
        check_rc(rc, 10);
    }

    packers--; //bind one more packer
    rc = pthread_mutex_unlock(&package_mutex);
    check_rc(rc, 10);

    access_screen("\nThe order %u is being packed.\n",  id, 0, 0, 1);

    sleep(Tpack * how_many_pizzas); //sleeping while the packager is working

    rc = pthread_mutex_lock(&package_mutex);
    check_rc(rc, 10);
    
    packers++; //seting a packer free, because he has finished his task

    rc = pthread_cond_signal(&package_cond);
    check_rc(rc, 10);
    
    rc = pthread_mutex_unlock(&package_mutex);
    check_rc(rc, 10);

    access_screen( "\nThe order %u was packed! \n" , id, 0, 0, 1);

    rc = pthread_mutex_lock(&ovens_mutex);
    check_rc(rc, 10);
    
    ovens += how_many_pizzas; //seting the ovens free, because they have finished their task

    rc = pthread_cond_signal(&ovens_cond);
    check_rc(rc, 10);
    
    rc = pthread_mutex_unlock(&ovens_mutex);
    check_rc(rc, 10);

    rc = clock_gettime(CLOCK_REALTIME, &packing);
    check_rc(rc, 10);

    preparation = (packing.tv_sec - customer_entity_packing.tv_sec); //calculate the time passed between the appearance of the costumer and the time slice, when his order was packed
    
    access_screen("\nThe order with id %u was prepared within %u minutes ", id, preparation, 0 ,2);
//------------------------------------------------------Deliverers---------------------------------------------------------------------------------------------------------
    sample = Tdellhigh - Tdellow + 1;
    delivery_time = Tdellow + (rand_r(&sample_seed) % sample); //generating a random number between Tdellow and Tdellhigh, which represents the required delivery time

    rc = pthread_mutex_lock(&delivery_mutex);
    check_rc(rc, 10); //check if the lock is done correctly (in a similar way we check and the unlock)
    
    while (deliverers == 0) { //while all the deliverers are unavailable, all the other threads wait untill at least one deliverer become available
        access_screen("\nThe order %u didn't find available delivery man. It is blocked...\n", id, 0, 0, 1);
        rc = pthread_cond_wait(&delivery_cond, &delivery_mutex);
        check_rc(rc, 10);
    }
    
    deliverers--; //bind one more deliverer
    rc = pthread_mutex_unlock(&delivery_mutex);
    check_rc(rc, 10);

    access_screen("\nThe order %u is being delivered.\n", id, 0, 0, 1);

    sleep(delivery_time); //sleeping while the deliverer is on the road

    rc = clock_gettime(CLOCK_REALTIME, &delivery);
    check_rc(rc, 10);
    deliveyy = (delivery.tv_sec - customer_entity_delivery.tv_sec); //calculate the time passed from the time, when the costumer appeared, to the time he recieved the pizzas he ordered

    access_screen("\nThe order with id %u has been delivered within %u minutes", id, deliveyy, 0, 2);

    rc = clock_gettime(CLOCK_REALTIME, &delivery_cooling);
    check_rc(rc, 10);
    cooling = (delivery_cooling.tv_sec - finish_baking.tv_sec); //calculate the time passed from the time the pizzas finished baking, to the time costumer takes the pizzas he ordered

    rc = pthread_mutex_lock(&statics_mutex);
    check_rc(rc, 10);
    
    sum_Delivery += deliveyy; //total time passed from costumer appearance to the time he took the pizzas he ordered
    if(max_Delivery < deliveyy) {
        max_Delivery = deliveyy;
    }
    
    sum_Cooling += cooling; //total time passed from time pizzas exited the oven to the time he took the pizzas he ordered
    if(max_Cooling < cooling) {
        max_Cooling = cooling;
    }
    
    rc = pthread_mutex_unlock(&statics_mutex);
    check_rc(rc, 10);

    sleep(delivery_time); //sleeping while the deliverer is on the road to return

    rc = pthread_mutex_lock(&delivery_mutex);
    check_rc(rc, 10);
    
    deliverers++; //make one deliverer availiable
    
    rc = pthread_cond_signal(&delivery_cond);
    check_rc(rc, 10);

    rc = pthread_mutex_unlock(&delivery_mutex);
    check_rc(rc, 10);
    
    pthread_exit(NULL);
}
//---------------------------------------------------------Main------------------------------------------------------------------------------------------------------------
int main (int argc, char *argv[]) {
    if(argc != 3) {
        printf("\nThe input is incorrect. Please try again. You should give 2 positive-number parameters, the number of clients and the seed for the generator of the random numbers.\n");
        exit(-1);
    }
    int Ncust = atoi(argv[1]); //convert string to int, number of threads requested by the user
    int seed = atoi(argv[2]); //convert string to int, the seed requested by the user
    if(Ncust <= 0 || seed <= 0){
        printf("\nThe input is incorrect. Please try again. You should give 2 positive-number parameters, the number of clients and the seed for the generator of the random numbers.\n");
        exit(-1);
    }
    unsigned int sample_seed = seed;
    unsigned int id[Ncust];
    unsigned int next_customer;
    unsigned int customer = 0;
    pthread_t threads[Ncust];
    unsigned int time[Ncust];
    time[0] = 0;
//------------------------------------------------------Mutex-cond-Innits-------------------------------------------------------------------------------------------------------
    rc = pthread_mutex_init(&pizza_maker_mutex, NULL); //initialize mutexes
    check_rc(rc, 0);
    rc = pthread_mutex_init(&ovens_mutex, NULL);//initialize condition variables
    check_rc(rc, 1);
    rc = pthread_mutex_init(&package_mutex, NULL);
    check_rc(rc, 2);
    rc = pthread_mutex_init(&delivery_mutex, NULL);
    check_rc(rc, 3);
    rc = pthread_mutex_init(&screen_mutex, NULL);
    check_rc(rc, 4);
    rc = pthread_mutex_init(&statics_mutex, NULL);
    check_rc(rc, 5);
    rc = pthread_cond_init(&pizza_maker_cond, NULL);
    check_rc(rc, 6);
    rc = pthread_cond_init(&ovens_cond, NULL);
    check_rc(rc, 7);
    rc = pthread_cond_init(&package_cond, NULL);
    check_rc(rc, 8);
    rc = pthread_cond_init(&delivery_cond, NULL); 
    check_rc(rc, 9);
//-----------------------------------------------------Thread-Creates------------------------------------------------------------------------------------------------------
    for (unsigned int i = 0; i < Ncust; i++) {
        id[i] = i + 1; //save the id of each thread in an array
        access_screen( "\nMain: thread creation %u", id[i], 0, 0, 1);
        struct arguments args = {&id[i], &seed, &time[i]}; //struct of specific arguments to pass in the order function
        rc = pthread_create(&threads[i], NULL, order, &args);
        check_rc(rc, 10);
        sample_seed += id[i];
        if (i < (Ncust - 1)) {
            next_customer = customer + Torderlow + rand_r(&sample_seed) % Torderhigh; //generating the time of a costumer appearance randomly
            time[i + 1] = next_customer;
            sleep(next_customer - customer);
            access_screen("\nThe client %u arrived after %u minutes", (i+2), (next_customer - customer), 0, 2);
            customer = next_customer;
        }
    }
    for (int i = 0; i < Ncust; i++) {
        rc = pthread_join(threads[i], NULL);
        check_rc(rc, 10);
    }
//---------------------------------------------------------Prints----------------------------------------------------------------------------------------------------------
    access_screen("\n\nTotal income from the sales is %u € (euros).", income, 0, 0, 1);
    access_screen("\nThere were sold %u plain pizzas and %u special pizzas", total_plain, total_special, 0, 2);
    access_screen("\nThe number of successful orders was %u and, the faild ones was %u", how_many_passed, how_many_failed, 0, 2);
    access_screen("\nThe average waiting time was %d minutes, and the maximum was %u minutes", (sum_Delivery/how_many_passed), max_Delivery, 0, 2);
    access_screen("\nThe average colding time was %d minutes, and the maximum was %u minutes\n", (sum_Cooling/how_many_passed), max_Cooling, 0, 2);
//--------------------------------------------------------Destroys---------------------------------------------------------------------------------------------------------
    destroyer(10);
    return 0;
}
