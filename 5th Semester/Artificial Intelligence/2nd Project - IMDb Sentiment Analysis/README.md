# Sentiment Analysis on IMDb Movie Reviews

This repository hosts a sentiment analysis project performed on IMDb movie reviews using machine learning algorithms. 
This project was carried out in the course "Artificial Intelligence", which is a required core course of the 3rd year of the Department of Computer Science in AUEB.

The datased used is the [IMDb movie review sentiment classification dataset](https://keras.io/api/datasets/imdb/). 
It consists of 25,000 movies reviews from IMDb, labeled by sentiment (positive/negative).

The machine learning algorithms we chose to implement are:
- Random Forest
- Logistic Regression
- Bernoulli Naive Bayes

We also implemented a Multilayer Perceptron (MLP) as a class that has the methods fit and predict.


## Overview

Under the folder src you can find our source code, that is, the implementation of all the algorithms mentioned above and the MLP.
Every algorithm is written in a Jupyter Notebook with all code cells ran, so you can easily see the outputs, 
mostly the learning curves, classifications reports, plots and comparisson plots.

In each notebook you will see the below:
- How we fetch and process the data from the IMDB database. (Each algorithm has its own hyperparameters, specifically selected to give the best results)
- The implemantation of the algorithm.
- Train the model using the train data set and then using the model to predict another set of data.
- The learning curve of the model
- Classification table, showing the metrics in each state of learning as the data set is growing.
- Classifications plots, showing diagrams for all the metrics (Accuracy, Recall, Percision, F1)
- Classifications plots for comparisson, showing the results of each metric of two algorithms in a plot.
- Algorithm for finding the best hyperparameters by optimazing the accuracy score in the validation data set.

You can also find our report to all of the above in the main branch, featuring the diagrams mentioned.
The report is written in 
[greek](https://github.com/EleniKechrioti/Imdb-classification-ML/blob/main/report_greek.pdf) and in 
[english](https://github.com/EleniKechrioti/Imdb-classification-ML/blob/main/report_english.pdf).

## How to run:
  1. Clone the project.
  2. Open the project from an IDE that supports Jupiter notebooks.
  3. Run the cells in the order they are.
> **Note** that some algorithms take up to half an hour to run completely.

## Collaborators 
- [Eleni Kechrioti](https://github.com/EleniKechrioti)
- [Maria Schoinaki](https://github.com/MariaSchoinaki)
- [Christos Stamoulos](https://github.com/ChristosStamoulos)

