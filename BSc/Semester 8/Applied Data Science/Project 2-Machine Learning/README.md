# ⚖️ LegalTextML: Machine Learning on Greek Legal Documents

This project is part of the Applied Data Science coursework (2025) at Athens University of Economics and Business (AUEB). It explores the use of both supervised and unsupervised machine learning methods on **Greek legal texts**, with a focus on document classification, clustering, and thematic analysis using LLMs.

---

## Overview

The project is divided into two main parts:

- **B1. Supervised Learning** - Legal document classification using traditional ML models and various text representations.
- **B2. Unsupervised Learning & Topic Analysis** - Clustering and theme extraction from Greek Supreme Court decisions using K-means and LLMs.

All tasks are applied on authentic legal corpora from Greek jurisdictions and implemented in Jupyter notebooks.

---

## Datasets Used

1. **Greek Legal Code** (from [AI-team-UoA/greek_legal_code](https://huggingface.co/datasets/AI-team-UoA/greek_legal_code))  
   - ~47,000 legal documents  
   - Labels: `volume`, `chapter`, `subject`

2. **Greek Legal Sum** (from [DominusTea/GreekLegalSum](https://huggingface.co/datasets/DominusTea/GreekLegalSum))  
   - Legal decisions from the Greek Supreme Court  
   - Metadata: `summary`, `case_category`, `case_tags`

---

## Phase B1 - Supervised Classification

### Models Implemented:
- **SVM** with TF-IDF and Bag-of-Words
- **Logistic Regression** with:
  - Word2Vec
  - FastText
- **Naive Bayes** with TF-IDF

### Evaluation Metrics:
- Accuracy
- Precision
- Recall
- F1-Score (weighted average)

### Highlights:
- Each model was tuned using Grid Search (C ∈ {0.01, 0.1, 1, 10})
- TF-IDF generally performed well for smaller label sets
- Dense embeddings (FastText) yielded more robust results for multi-class setups

---

## Phase B2 - Clustering & Thematic Topic Extraction

### 1. Exploratory Data Analysis (EDA)
- Frequency distributions for categories and tags
- Co-occurrence heatmaps and boxplots
- Complexity indicators from tag counts and summary lengths

### 2. K-Means Clustering
- TF-IDF vectorization (max 3000 features)
- Clustering evaluated using:
  - **Silhouette Score** (best at K=18)
  - **NMI** (best at K=20)
- PCA visualizations show soft boundaries typical for legal text clustering

### 3. LLM-Based Theme Extraction
- Used **Llama-Krikri-8B-Instruct** via Unsloth (Colab-compatible)
- Few-shot learning (3 examples per cluster) to generate Greek "Θέμα" (theme) for each group
- Compared random vs centroid-based representative samples
- Clusters visualized with pie charts and word clouds

---

## 🛠️ Tech Stack

| Component      | Tools Used                               |
|----------------|-------------------------------------------|
| Programming    | Python 3.x, Jupyter Notebooks             |
| ML Libraries   | Scikit-learn, Gensim, FastText            |
| NLP Tools      | NLTK, SpaCy                               |
| LLM Interface  | HuggingFace Transformers, Unsloth         |
| Clustering     | KMeans, Silhouette Score, NMI             |
| Visualization  | Matplotlib, Seaborn, Wordcloud, PCA       |