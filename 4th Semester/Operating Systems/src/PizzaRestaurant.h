//------------------------------------------------------Includes-----------------------------------------------------------------------------------------------------------
#include <stdio.h>
#include <pthread.h>
#include <unistd.h>
#include <stdlib.h>
#include <time.h>
//-----------------------------------------------------Definitions---------------------------------------------------------------------------------------------------------
#define Ncook 2
#define Noven 15
#define Npacker 2
#define Ndeliverer 10
#define Torderlow 1
#define Torderhigh 3
#define Norderlow 1
#define Norderhigh 5
#define Pplain 0.6
#define Tpaymentlow 1
#define Tpaymenthigh 3
#define Pfail 0.1
#define Cplain 10
#define Cspecial 12
#define Tprep 1
#define Tbake 10
#define Tpack 1
#define Tdellow 5
#define Tdellhigh 15
//------------------------------------------------------Mutexes-----------------------------------------------------------------------------------------------------------
pthread_mutex_t pizza_maker_mutex;
pthread_mutex_t ovens_mutex;
pthread_mutex_t package_mutex;
pthread_mutex_t delivery_mutex;
pthread_mutex_t statics_mutex;
pthread_mutex_t screen_mutex;
pthread_cond_t pizza_maker_cond;
pthread_cond_t ovens_cond;
pthread_cond_t package_cond;
pthread_cond_t delivery_cond;
//-----------------------------------------------------Resourses-----------------------------------------------------------------------------------------------------------
unsigned int pizza_makers = Ncook;
unsigned int ovens = Noven;
unsigned int packers = Npacker;
unsigned int deliverers = Ndeliverer;
//-----------------------------------------------------Statistics-----------------------------------------------------------------------------------------------------------
int rc;
unsigned int income = 0;
unsigned int total_special = 0;
unsigned int total_plain = 0;
unsigned int how_many_failed = 0;
unsigned int how_many_passed = 0;
unsigned int sum_Delivery = 0;
unsigned int max_Delivery  = 0;
unsigned int sum_Cooling = 0;
unsigned int max_Cooling = 0;
//---------------------------------------------------Struct-for-order------------------------------------------------------------------------------------------------------
struct arguments {
    unsigned int * idp;
    unsigned int * seedp;
    unsigned int * time;
};
//-------------------------------------------------------Functions----------------------------------------------------------------------------------------------------------
void check_rc(int rs, int thread_count);
void access_screen(void* arg, unsigned int arg1, unsigned int arg2, unsigned int arg3, unsigned int count);
void destroyer(int thread_count);
