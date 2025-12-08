# Text Classification & POS Tagging with MLPs

# Tweet Sentiment Classification with MLPs

For this assignment, we used the **Twitter Sentiment Analysis** dataset from Kaggle (~100K tweets, binary sentiment).  
The same dataset was used in Assignment 2, so only a brief preprocessing summary is provided here.

---

## 1. Data Exploration  
- Roughly balanced classes (≈43.5% negative / ≈56.5% positive).  
- Tweets are short (mean ≈ 13 words).  

---

## 2. Data Preprocessing  

### **Text Cleaning (regex + spaCy):**
- Remove mentions, hashtags, quotes, URLs, HTML entities  
- Remove non-word characters, redundant spaces  
- Lowercase normalization  

### **Linguistic Normalization**
- spaCy POS tagging and tokenization  
- Lemmatization only  

Output: clean, lemmatized tweet lists.

---

## 3. Experimental Setup

- **Corpus:** ~100K tweets (Kaggle)  
- **Splits:** 70% train, 15% dev, 15% test (stratified, `random_state=2025`)  
- **Preprocessing:** cleaning + lemmatization pipeline  

We compare:
1. **Majority Baseline**  
2. **Logistic Regression (TF-IDF, tuned in Assignment 2)**  
3. **Our PyTorch MLP (TF-IDF → SVD → MLP)**  

---

## 4. Feature Extraction

### **TF-IDF n-gram vectors**
- scikit-learn `TfidfVectorizer`  
- Sparse ~30,000-dimensional representa­tion

### **Dimensionality Reduction**
We project TF-IDF into a dense space:

TruncatedSVD(n_components=500, random_state=2025)

Final MLP input size = **500 dense features**

---

## 5. Baseline Models

### **Majority Class Baseline**
- `DummyClassifier(strategy="most_frequent")`
- Always predicts majority class  
- Used as a lower bound

### **Logistic Regression (TF-IDF)**
- `SelectKBest` with **k=30,000**  
- `LogisticRegression(C=1)`  
- Strong linear baseline  

---

# MLP Architecture & Training

## MLP Configuration (best final model)
- **Input:** 500-dim SVD vectors  
- **Hidden layers:** `[512]`  
- **Activation:** ReLU  
- **Dropout:** ≈ **0.7** (tuned)  
- **Output:** 2-unit linear layer  
- **Loss:** Cross-entropy  
- **Batch size:** 256  
- **Optimizer:** Adam (`lr=1e-3`)  

---

## Hyperparameter Tuning

### 1. **Architecture Search**
Evaluated:  
`[512]`, `[512,256]`, `[1024,512,256]`, `[256]`, `[1024,1024]`  
Best trade-off: **single hidden layer [512]**

### 2. **Dropout Search**
- Range: **0.1 → 1.0**  
- Best: **≈0.7**, suggesting strong regularization is needed.

### 3. **Epoch Tuning**
- Up to 200 epochs  
- Early stopping (patience 5)  
- Best epoch ≈ **26**

### 4. **Final Training**
- Hidden Layer: 512  
- Dropout: 0.7  
- Epochs: 26  
- Metrics logged: loss, accuracy, macro-F1

---

## Loss & Metric Curves
Observed patterns:
- Training loss decreases steadily.  
- Dev loss diverges slightly → mild overfitting.  
- Dev accuracy and F1 plateau early.  

---

# Evaluation

We compute:
- Per-class **Precision, Recall, F1, PR-AUC**
- **Macro-averaged** P/R/F1/PR-AUC
- Comparisons on: train / dev / test splits  

### Summary of Findings
- **Majority baseline**: very weak (Macro-F1 ≈ 0.36).  
- **LogReg (TF-IDF)**: strongest model (Macro-F1 ≈ 0.765).  
- **MLP (TF-IDF + SVD)**: good but below LogReg (Macro-F1 ≈ 0.71).  

**Why LogReg outperforms MLP:**  
Sparse high-dimensional TF-IDF features favor linear decision boundaries.  
MLP might outperform if trained on **dense semantic embeddings** → a direction for future improvements.

---

# POS Tagging with MLPs

Dataset: **UD Ancient Greek PROIEL Treebank**  
- Train: 15,016 sentences  
- Dev: 1,019 sentences  
- Test: 1,047 sentences  
- Tokens annotated with UPOS tags  

---

## Preprocessing

### **Context Windows**
- ±2 window around target word  
- Special padding tokens  

### **Vocabularies**
- Word vocabulary (with PAD/UNK)  
- UPOS tag set vocabulary  
- Index mappings used for the MLP model  

### **Embeddings**
Two methods tested:
1. Random MLP-trained embeddings  
2. **Pretrained Word2Vec embeddings (chosen)**

**Word2Vec settings:**
- Skip-gram  
- 100-dim  
- Window=5  
- Min freq = 1  
- Epochs = 10  

---

# POS Tagger MLP Model

## Architecture
- Input: concatenated embeddings for 5-word window  
- Embeddings initialized with Word2Vec  
- Hidden layers:  
  - **1 hidden layer, 256 units**  
  - ReLU  
  - Dropout **0.3**  
- Output: logits for all UPOS tags  
- Loss: cross-entropy  
- Optimizer: Adam  

---

## Training Procedure
- Mini-batch training  
- Dev-set evaluation after each epoch  
- Early stopping based on dev loss  
- Model restored to best dev macro-F1 checkpoint  

---

## Hyperparameter Search
Searched over:
- Number of hidden layers  
- Hidden size  
- Dropout rates  

Best model:
- **5-word window**  
- **100-dim embeddings**  
- **1 hidden layer (256 units)**  
- **Dropout 0.3**

---

## Results & Observations

### Model Comparison (POS Tagging)
- **Train:** MLP > Logistic Regression > Baseline  
- **Dev/Test:**  
  - MLP slightly > LogReg  
  - Both far > baseline  

### Notes
- Baseline strong for frequent tags  
- LogReg provides boost via context window + embeddings  
- MLP learns nonlinear interactions → highest macro-F1 and PR-AUC  
- Some overfitting visible in loss curves but mitigated by early stopping  

---

# Summary

### Tweet Classification
- Best model: **TF-IDF Logistic Regression**  
- MLP strong but overfits SVD-reduced features  
- Future improvement: **use dense pretrained embeddings (e.g., GloVe/BERT)**

### POS Tagging
- Best model: **MLP with pretrained Word2Vec**  
- Outperforms window-based Logistic Regression  
- Benefits from nonlinear modeling + pretrained lexical semantics  