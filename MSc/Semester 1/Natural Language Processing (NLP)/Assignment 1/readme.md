# Assignment 1 — n‑Gram Language Models (NLP)

This repository contains the notebook **`nlp_1.ipynb`**, an implementation‑focused walkthrough of **add‑alpha (Laplace) n‑gram language models** with training, evaluation, and autocompletion — aligned with the assignment spec:

> **Assignment 1 - Natural Language Processing — MSc. Computer Science, AUEB [2025–2026]**

The notebook uses the **NLTK Brown** corpus, builds **unigram / bigram / trigram** models with **UNK** handling and **start/end tokens**, evaluates **cross‑entropy** and **perplexity**, and demonstrates **greedy** and **beam‑search** autocompletion. It also includes guidance on **numerical stability** via log‑probabilities.

---

## Data & Preprocessing

- **Corpus:** `nltk.corpus.brown`
- **Sentence splitting:** `nltk.sent_tokenize`
- **Tokenization:** `nltk.TweetTokenizer` (robust to punctuation, social text)
- **n‑gram utilities:** `nltk.util.ngrams`
- **Splits:** train / dev / test via `train_test_split` (scikit‑learn)

**Special tokens used**
- `START` for bigrams, `START1` & `START2` for trigrams, `END` for sentence termination
- `UNK` for out‑of‑vocabulary items (via `_map_oov`)

---

## Requirements

The notebook installs/uses:
- `nltk`
- `numpy`
- `scikit-learn` (for `train_test_split`)
- (standard libs) `re`, `math`, `collections`, `typing`

Install the Python dependencies:
```bash
pip install nltk numpy scikit-learn
```

Download the required NLTK resources (run inside Python once):
```python
import nltk
nltk.download('brown')
nltk.download('punkt')
```

---

## How to Run

1. Open the notebook:
   ```bash
   jupyter notebook "nlp_1.ipynb"
   ```

2. Run cells **top‑to‑bottom**. The notebook will:
   - Load & tokenize **Brown** corpus
   - Build n‑gram counts with **UNK** and start/end tokens
   - Train **unigram/bigram/trigram** models
   - **Tune α ≤ 1** on the development set
   - Report **Cross‑Entropy** and **Perplexity** on the test set
   - Show **greedy** and **beam‑search** autocompletion examples