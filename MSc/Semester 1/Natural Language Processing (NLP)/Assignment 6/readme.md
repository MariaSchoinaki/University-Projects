# Text Classification & POS Tagging with BERTs  
**Tweet Sentiment Classification & Ancient Greek UPOS Tagging**

This repository contains the implementation for an NLP assignment focusing on **pretrained Transformer-based models (BERT family)** applied to:

1. **Tweet Sentiment Classification (Binary)**
2. **Ancient Greek POS Tagging (UPOS)**

The assignment explores **parameter-efficient fine-tuning**, strong classical and neural baselines, and compares them against **BERT-based models with LoRA adapters**, as well as exploratory **LLM prompting experiments**.

All experiments follow a consistent workflow:  
**data preparation → baselines → pretrained models → tuning → evaluation**.

---

## Part 1 — Tweet Sentiment Classification

### Dataset
- **Source:** Twitter Sentiment Analysis (Kaggle)
- **Size:** ~100,000 tweets
- **Labels:**  
  - `0` = Negative  
  - `1` = Positive
- **Splits:**  
  - 70% train  
  - 15% development  
  - 15% test  
  - Stratified by label (`random_state = 2025`)

---

### Preprocessing
- Raw tweet text is used directly.
- Tokenization with the **DistilRoBERTa tokenizer** (Hugging Face):
  - Subword tokenization
  - Automatic special tokens
  - Truncation to maximum sequence length
- Dynamic batch padding using a data collator.
- Inputs formatted as PyTorch tensors (`input_ids`, `attention_mask`, `labels`).

---

### Baseline Models
The following baselines from previous assignments are included for comparison:

1. **Majority baseline** (`DummyClassifier`)
2. **TF-IDF + Logistic Regression**
   - Word n-grams (uni/bi/tri)
   - `SelectKBest` with mutual information (`k = 30,000`)
3. **MLP over TF-IDF features**
   - SVD (500 dimensions)
   - Hidden layer: 512 units, ReLU
   - Dropout: 0.7
4. **BiLSTM with pretrained word embeddings**
5. **Stacked CNN with pretrained word embeddings**
   - Residual convolutional blocks
   - Global max pooling
   - MLP classifier head

---

### Main Model — BERT + LoRA Sentiment Classifier

#### Transformer Backbone
- Model: `distilroberta-base`
- 6 encoder layers
- All encoder layers **frozen** during training

#### Parameter-Efficient Fine-Tuning (LoRA)
- LoRA adapters applied to attention:
  - Query and value projections
- Hyperparameters:
  - Rank `r = 8`
  - Scaling factor `α = 32`
  - Dropout = 0.2
- Only LoRA parameters and the classification head are trainable

#### Classification Head
- Linear layer on pooled sentence representation
- Output dimension: 2
- Loss: cross-entropy

---

### Training & Hyperparameter Tuning
- Implemented with the **Hugging Face Trainer API**
- Optimizer: AdamW (default)
- Batch size: 64
- Max epochs: 20
- Early stopping on **dev Macro-F1** (patience = 2)
- Hyperparameters tuned:
  - Learning rate
  - LoRA dropout
  - Number of frozen encoder layers

**Best configuration:**
- Fully frozen encoder
- Learning rate: `1e-3`
- LoRA rank: `8`
- Dev Macro-F1 ≈ **0.84**

---

### Evaluation
- Metrics:
  - Precision / Recall / F1
  - PR-AUC
- Macro-averaged metrics used for comparison
- Evaluation on train, dev, and test sets

**Key result:**  
BERT + LoRA outperforms all classical and neural baselines while remaining parameter-efficient.

---

### LLM Prompting (Exploratory)
- Few-shot prompt-based sentiment classification
- 10 randomly sampled test tweets
- Results:
  - Accuracy ≈ 0.90
  - Macro-F1 ≈ 0.90
- Demonstrates competitive performance without fine-tuning

---

## Part 2 — POS Tagging (Ancient Greek, UPOS)

### Dataset
- **UD Ancient Greek PROIEL Treebank**
- Sentences:
  - Train: 15,016
  - Dev: 1,019
  - Test: 1,047
- Labels: Universal POS tags (UPOS)

---

### Preprocessing
- Sentences tokenized using BERT subword tokenizer
- Alignment of word-level UPOS tags to subwords
- Padding and non-initial subword tokens ignored in loss

---

### Baseline & Neural Models
- Per-word frequency baseline
- Window-based MLP tagger
- BiLSTM taggers (word-only, character-aware)
- CNN sequence taggers:
  - Word-only
  - Character-aware
  - Temporal averaging (CNN-TA)

---

### Main Model — BERT POS Tagger
- Pretrained BERT encoder for token classification
- Task-specific MLP + linear classifier head
- Partial encoder freezing explored
- Loss computed only on valid tokens

**Best configuration:**
- Learning rate: `2e-5`
- Batch size: 8
- Weight decay: 0.01
- Early stopping (patience = 2)
- Dev Macro-F1 ≈ **0.98**

---

### Evaluation
- Metrics:
  - Accuracy
  - Macro-F1
  - Micro-F1
- Results reported for train, dev, and test splits

**Key findings:**
- BERT consistently outperforms CNN- and BiLSTM-based models
- Strong performance on rare and morphologically complex tags

---

### LLM Prompting (Exploratory)
- Prompt-based Ancient Greek POS tagging
- Structured UPOS output format
- Few-shot demonstrations
- Competitive results on a small test sample

---

## Final Conclusions
- Parameter-efficient **BERT + LoRA** fine-tuning yields state-of-the-art results.
- Classical baselines remain strong but are surpassed by pretrained transformers.
- For POS tagging, contextualized representations are essential for morphologically rich languages.
- Prompt-based LLMs show promising performance even without fine-tuning.