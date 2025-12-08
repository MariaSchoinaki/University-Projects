# Text Classification & POS Tagging with RNNs  
**Tweet Sentiment Classification & Ancient Greek POS Tagging using BiLSTM Models**  

This README summarizes the full workflow, preprocessing, model architectures, experiments, and evaluation for both NLP tasks implemented in this project.  


---

# Part 1 — Tweet Sentiment Classification (Binary)

## Dataset
- **Source:** Twitter Sentiment Analysis (Kaggle)  
- **Size:** ~100,000 tweets  
- **Labels:** 0 = Negative, 1 = Positive  
- **Split:** 70% train, 15% dev, 15% test (stratified, random_state=2025)

---

## Preprocessing Pipeline
We apply a standardized cleaning + normalization pipeline:

### Text Cleaning
- Remove @mentions, hashtags, HTML artifacts, URLs  
- Remove non-word characters & excess spaces  
- Lowercasing  

### Linguistic Normalization
- spaCy tokenizer  
- Lemmatization (en_core_web_sm)  

Result: clean tokenized corpus used for vocabulary construction and embedding training.

---

## Embeddings
We train **Word2Vec skip-gram** embeddings:

- dimension: 100  
- min_count: 1  
- window: 5  
- epochs: 10  

Vocabulary size: **34,197 tokens**, plus `<pad>` and `<unk>`.

Embedding matrix is loaded into a **trainable** PyTorch `nn.Embedding` layer.

---

## Model Architectures

### **Baselines**
1. **Majority Class Baseline**  
2. **TF-IDF + Logistic Regression**  
3. **MLP + SVD-reduced TF-IDF features**

### **Main Model — BiLSTM + Self-Attention**
- Embedding dropout: 0.2  
- **2-layer BiLSTM**, 128 hidden units per direction  
- Dense **self-attention**  
- Classifier: Linear → ReLU → Dropout(0.2) → Linear(2)  

Batch size: 256  
Optimizer: Adam  
Loss: Cross-entropy  

### Training Features
- Packed padded sequences  
- Gradient clipping  
- Early stopping on **dev Macro-F1** (patience=5)  
- Full logging of loss, accuracy & F1 curves  

---

## Hyperparameter Search (Random Search)
8 random configurations sampled across:
- hidden size  
- number of LSTM layers  
- dropout  
- learning rate  
- fine-tuning of embeddings  

### **Best configuration**
- hidden_size = 128  
- num_layers = 2  
- dropout = 0.2  
- learning_rate = 0.01  
- attn_hidden_dim = single dense layer  
- fine_tune_embeddings = True  
- **Dev Macro-F1 ≈ 0.76**

---

## Results Summary

### **Development Set**
| Model | Macro-F1 | Macro PR-AUC |
|-------|---------|---------------|
| Majority Baseline | ~0.36 | 0.50 |
| TF-IDF + LR | ~0.764 | 0.841 |
| MLP | ~0.713 | 0.786 |
| **BiLSTM + Attention** | **~0.765** | **0.846** |

### **Test Set**
- BiLSTM closely matches TF-IDF+LR  
- MLP underperforms both  
- Baseline heavily underperforms  
  
*BiLSTM + Attention generalizes well and is competitive with strong linear baselines.*

---

# Part 2 — POS Tagging (Ancient Greek)

## Dataset
- **UD Ancient Greek PROIEL Treebank**  
- Train: 15,016 sentences  
- Dev: 1,019 sentences  
- Test: 1,047 sentences  
- Labels: Universal POS Tags (UPOS)

---

## Preprocessing
### For MLP:
- Sliding windows of 5 tokens  
- Padding with `<PAD>`  

### For LSTM-based models:
- Full-sentence sequences  
- Padded batches, mask-aware loss  

---

## Embeddings for POS Tagging
Word2Vec skip-gram:
- dim: 100  
- window: 5  
- min_count: 1  
- epochs: 10  

Used to initialize **trainable** embedding layer.

---

# POS Tagging Models

## MLP Window-Based Classifier
- Context window: (w–2 … w … w+2)  
- Predicts UPOS tag for center token  

## BiLSTM Tagger (Main Model)
- 1–2 layer BiLSTM  
- Hidden size tuned (best: 128)  
- Dropout: 0.3  
- Optimizer: Adam (1e-3)  
- Early stopping based on dev Macro-F1  

## Character-Aware BiLSTM
- Char embeddings + character BiLSTM  
- Combined with word embeddings  
- Improved handling of morphology & OOV words  

## BiLSTM with Temporal Averaging
- Maintains running averaged weights  
- Tested for stability improvements  

---

# POS Tagging Results

### Train Set
MLP > BiLSTM-TA > BiLSTM > BiLSTM-char > Baseline

### Development Set (Generalization)
**BiLSTM-char > BiLSTM-TA ≈ BiLSTM > MLP > Baseline**

### Test Set
- **BiLSTM-char achieves best Macro-F1**
- BiLSTM-TA and standard BiLSTM close behind  
- MLP competitive but weaker  
- Baseline struggles with rare tags  

  
*Sequence modeling with BiLSTMs significantly outperforms window-based MLPs.*  
*Character-aware models yield the strongest generalization due to morphological awareness.*

---

# Final Conclusions

### Tweet Classification
- BiLSTM + Attention performs nearly as well as TF-IDF + LR  
- Outperforms MLP and far surpasses the baseline  
- Random search effectively identified a strong configuration  

### POS Tagging
- Sequence models outperform window-based approaches  
- Character-aware BiLSTM provides the best UPOS tagging performance  
- Temporal averaging marginally improves stability  