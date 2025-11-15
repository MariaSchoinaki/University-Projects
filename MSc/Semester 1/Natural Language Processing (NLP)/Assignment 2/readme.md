# Tweet Sentiment Analysis

This project performs sentiment classification on Twitter posts using Natural Language Processing (NLP) and machine learning methods. The workflow includes data exploration, text preprocessing, feature extraction using TF-IDF representations, model training, hyperparameter tuning, evaluation, and performance visualization.

## Dataset
The dataset used is the **Twitter Sentiment Analysis** dataset from Kaggle.  
It contains approximately **100,000 tweets**, each labeled with:
- `sentiment = 0` → negative
- `sentiment = 1` → positive

The dataset was split into:
- **Train:** 70%
- **Development:** 15%
- **Test:** 15%

## Data Preprocessing

We clean and normalize each tweet to remove noise and standardize text:
- Removal of mentions (@username), hashtags (#tag), URLs, HTML entities, and non-word characters.
- Lowercasing all tokens.
- Lemmatization using **spaCy** with POS tagging for context-based normalization.

Note: Informal spelling, slang, and elongated expressions common in tweets are retained when normalization is ambiguous.

## Feature Extraction

We represent text using:
- **TF-IDF n-gram features** (unigrams, bigrams, trigrams)
- Vocabulary limited to **30,000 features**
- `sublinear_tf=True` to reduce the impact of highly frequent terms

For model interpretability and visualization:
- **Information gain** scores identify the most discriminative features.
- **TruncatedSVD** (n_components = 2) is applied to reduce dimensionality for plotting high-dimensional vectors.

## Models Implemented

| Model | Feature Representation | Notes |
|------|------------------------|-------|
| Majority Baseline | None | Always predicts the most frequent label. |
| Logistic Regression (Boolean) | Binary Bag-of-Words | Uses word presence only. |
| Logistic Regression (TF-IDF, Tuned) | TF-IDF (uni/bi/tri) | Best model after hyperparameter tuning. |
| k-Nearest Neighbors (KNN) | TF-IDF (uni/bi/tri) | Uses cosine similarity; prone to overfitting. |

### Hyperparameter Tuning
Performed with `GridSearchCV` using a **PredefinedSplit** to ensure the development set remains separate.  
Best configuration:

C = 1
k = 30000 (SelectKBest on mutual information)
n-grams = (1, 2, 3)

## Results

The **TF-IDF Logistic Regression** model achieved the best performance:

- **Macro F1 (Test): ~0.766**
- **Macro PR-AUC (Test): ~0.843**

KNN showed strong overfitting (near-perfect train score, weaker test score).  
Boolean LR and the baseline performed significantly lower than TF-IDF LR.

## Key Observations
- Character and word n-grams are important for modeling informal language in tweets.
- TF-IDF weighting significantly improves predictive performance compared to Boolean features.
- KNN is not suitable for high-dimensional sparse text classification.
- Negative tweets are harder to classify, with lower recall than positive tweets across models.