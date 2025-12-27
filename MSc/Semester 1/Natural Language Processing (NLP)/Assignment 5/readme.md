# Text Classification & POS Tagging with CNNs  
**Tweet Sentiment Classification (Binary) & Ancient Greek UPOS Tagging using Stacked CNN Models**  

This notebook project contains two end-to-end NLP pipelines:

1. **Tweet sentiment classification** (Twitter dataset)  
2. **Ancient Greek POS tagging (UPOS)** (UD PROIEL treebank)  

Both tasks follow a full workflow: preprocessing -> vocabulary/embeddings -> baselines -> neural sequence models (stacked CNNs) -> hyperparameter tuning -> evaluation.

---

# Part 1 — Tweet Sentiment Classification

## Dataset
- **Source:** Twitter Sentiment Analysis (Kaggle)
- **Size:** ~100,000 tweets
- **Labels:** `0 = Negative`, `1 = Positive`
- **Split:** 70% train, 15% dev, 15% test (**stratified**, `random_state=2025`)

---

## Preprocessing

### Text Cleaning (regex-based)
- Remove user mentions (`@...`), hashtags, HTML artifacts, and URLs
- Remove non-word characters and extra spaces
- Lowercase text

### Linguistic Normalization (spaCy)
- Tokenization + lemmatization using `en_core_web_sm`

Output: cleaned, lemmatized token sequences per tweet.

---

## Vocabulary & Embeddings
- Build a vocabulary from the cleaned training corpus
- Add special tokens: `<pad>`, `<unk>`

### Word2Vec (skip-gram)
Trained on the cleaned training corpus using:
- **dim:** 100  
- **window:** 5  
- **min_count:** 1  
- **epochs:** 10  

Embeddings initialize a **trainable** `nn.Embedding` layer (fine-tuned during training).

---

## Models Compared

### Baselines
1. **Majority baseline** (`DummyClassifier(most_frequent)`)
2. **TF-IDF + Logistic Regression**
   - `SelectKBest(mutual_info_classif, k=30000)`
   - `LogisticRegression(C=1)`
3. **MLP over TF-IDF (SVD features)**
   - `TruncatedSVD(n_components=500)`
   - Hidden layer: 512, ReLU
   - Dropout: 0.7
   - Cross-entropy loss

### Neural Models (Embedding-based)
4. **BiLSTM (embedding baseline)**
   - Uses pretrained Word2Vec embeddings
   - BiLSTM encoder, global average pooling, linear classifier

5. **Main Model — Stacked Residual CNN Classifier**
A word-level CNN encoder built from **stacked residual convolution blocks**:

**CNN block structure**
- Parallel 1D convolutions with kernel sizes **(2, 3)**
- Output channels per filter: **64**
- ReLU activations + concatenation
- Projection back to embedding dimension
- Residual connection + layer normalization

**Pooling + classifier**
- Global max pooling over time
- Head: Linear -> ReLU -> Dropout -> Linear(2)

**Best CNN configuration (random search)**
- `num_blocks = 2`
- `kernel_sizes = (2, 3)`
- `out_ch_per_filter = 64`
- `dropout = 0.4`
- `lr = 0.01`
- `fine_tune_embeddings = True`
- Batch size: 256

---

## Training Setup
- Optimizer: Adam
- Loss: cross-entropy
- Gradient clipping
- Early stopping on **dev Macro-F1** (patience = 5)
- Full logging: train/dev loss, accuracy, macro-F1

---

## Hyperparameter Search (Random Search)
Random sampling over:
- `num_blocks ∈ {2,3,4}`
- `kernel_sizes ∈ {(2,3), (2,3,4)}`
- `out_ch_per_filter ∈ {64,128,256}`
- `dropout ∈ {0.2,0.4,0.6}`
- `lr ∈ {1e-2,1e-3,1e-4}`

Model selection metric: **dev Macro-F1**.

---

## Evaluation
- Report per-class Precision / Recall / F1 / PR-AUC
- Macro-averaged metrics reported as the main comparison

---

# Part 2 — POS Tagging (Ancient Greek, UPOS)

## Dataset
- **UD Ancient Greek PROIEL Treebank**
- Train: **15,016** sentences  
- Dev: **1,019** sentences  
- Test: **1,047** sentences  
- Labels: **UPOS** tags

---

## Preprocessing
- Convert sentences into token sequences + UPOS tag sequences
- Build vocabularies:
  - `word2idx` with `<PAD>`, `<UNK>`
  - `tag2idx` for UPOS labels
- Pad sentence batches and ignore `<PAD>` in the loss

---

## Embeddings
### Word2Vec (skip-gram)
Trained on training tokens:
- **dim:** 100  
- **window:** 5  
- **min_count:** 1  
- **epochs:** 10  

Used to initialize a **trainable** embedding layer.

---

## Models Compared

### Baselines
1. **Per-word baseline** (dictionary / most-frequent tag per word, fallback to global majority)

### Window MLP Tagger
2. **MLP (window-based)**
- Sliding window size = 5 (w−2…w…w+2)
- Best configuration:
  - `hidden_sizes = [256]`
  - `dropout = 0.3`
  - `lr = 1e-3`
  - `batch_size = 128`
  - `epochs = 20`

### Sequence CNN Taggers
3. **Word-only Stacked CNN Tagger**
- Residual CNN blocks over full sentences (sequence labeling)
- Best searched configs include:
  - `hidden_dim ∈ {256, 384}`
  - `num_blocks ∈ {3, 4}`
  - `kernel_sizes ∈ {(2,3,4), (3,4,5)}`
  - Dropout in {0.3, 0.4}
- Best-performing word-only config used:
  - `hidden_dim = 256`
  - `num_blocks = 4`
  - `kernel_sizes = (2,3,4)`
  - `dropout = 0.4`
  - `lr = 7e-4`
  - `batch_size = 64`

4. **Character-aware CNN Tagger**
Adds a CharCNN encoder per token:
- `char_emb_dim = 30`
- `char_kernel_sizes = (3,4,5)`
- `char_num_filters ∈ {50, 64}`
Character representation is concatenated with word embedding and projected before CNN blocks.

5. **CNN with Temporal Averaging (CNN-TA)**
- Maintains running average of model weights across training
- Evaluates averaged-weights model for stability/generalization gains

---

## Training Setup
- Optimizer: Adam
- Loss: cross-entropy **ignoring padded positions**
- Early stopping (patience = 5)
- Model selection primarily by **dev Macro-F1**
- Histories tracked for loss and Macro-F1

---

## Results Summary (Qualitative)
- Window MLP is competitive but limited (local context only)
- Sequence CNN models generalize better on dev/test
- **Character-aware CNN improves robustness** (morphology + OOV handling)
- Temporal averaging provides small, consistent stability gains in some runs

---

# Final Conclusions

## Tweet Sentiment
- Strong linear baseline (**TF-IDF + LR**) remains very hard to beat
- Stacked residual CNN with pretrained embeddings is competitive and robust
- Random search effectively identified a strong CNN configuration

## Ancient Greek POS Tagging
- Sequence tagging with stacked CNNs outperforms non-sequence baselines
- Character-aware modeling improves generalization due to morphology
- Temporal averaging can improve stability and sometimes dev/test performance