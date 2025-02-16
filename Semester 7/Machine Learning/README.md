# **Multiclass Text Classification - Machine Learning Project**

## **Overview**
This project focuses on **multiclass text classification**, applying various **machine learning algorithms** to categorize textual data into multiple classes. The goal is to implement, compare, and evaluate different **classification models**, leveraging **feature extraction, dimensionality reduction, and deep learning techniques**.

## **Key Topics Covered**
- **Dimensionality Reduction & Feature Engineering**
  - Principal Component Analysis (PCA) for feature reduction
  - Tokenization, stopword removal, and TF-IDF vectorization
- **Machine Learning Models**
  - Least Squares Classification (LS)
  - Logistic Regression with Stochastic Gradient Descent (SGD)
  - K-Nearest Neighbors (KNN) with hyperparameter tuning
  - Naïve Bayes with Gaussian distribution assumptions
  - Support Vector Machines (SVM) using One-vs-Rest approach
  - K-Means clustering for unsupervised learning
- **Deep Learning for Text Classification**
  - Implementing a **Multilayer Perceptron (MLP)** with PyTorch
  - Training models using **Stochastic Gradient Descent (SGD)**
  - Using **Cross Entropy Loss** for classification

## **Tasks & Concepts**
### **1. Principal Component Analysis (PCA)**
- Applied PCA to reduce dataset dimensions.
- Chose **m** principal components where **2 ≤ m < n**.
- Visualized variance retained for different values of **m**.

### **2. Machine Learning Models Implementation**
- **Least Squares Classification**:
  - Adapted least squares regression for classification tasks.
- **Logistic Regression (LR)**:
  - Implemented using **SGD optimization** with Cross Entropy Loss.
- **K-Nearest Neighbors (KNN)**:
  - Tuned **K in range [1, 10]** and evaluated model performance.
- **Naïve Bayes (NB)**:
  - Used **Gaussian distributions with diagonal covariance matrices**.
- **Support Vector Machines (SVMs)**:
  - Implemented **One-vs-Rest SVM classifiers**.
  - Chose final label based on the highest classification score.
- **K-Means Clustering**:
  - Performed clustering on dataset **without label supervision**.
  - Selected number of clusters **equal to dataset class count**.

### **3. Deep Learning Model - Multilayer Perceptron (MLP)**
- Implemented an **MLP network with customizable layers**.
- Chose **activation functions, learning rate, and hyperparameters.**
- Trained using **SGD optimizer with Cross Entropy Loss.**

### **4. Model Evaluation & Results Interpretation**
- Compared different models' performance.
- Visualized training loss and accuracy per epoch.
- Discussed challenges, insights, and potential improvements.

---

## **Tools & Technologies Used**
- **Python (Pandas, NumPy, Matplotlib, Seaborn)** for data analysis
- **Scikit-learn** for traditional ML models
- **PyTorch** for deep learning (MLP implementation)
- **NLTK / spaCy** for text preprocessing

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)
- **Developer:** [Nikos Mitsakis](https://github.com/NIKOMAHOS)