# ⚖️ GreekSupremeCourtScraper

A web scraping and data collection project for Supreme Court decisions in Greece, developed for the **Applied Data Science** course (2025) at Athens University of Economics and Business (AUEB). The goal is to extract and structure decisions from the [Areios Pagos website](https://www.areiospagos.gr), focusing on legal metadata and textual sections for further analysis.

---

## Project Overview

This project performs web crawling and scraping of legal decisions issued in **2024**, covering both **Civil** and **Criminal** court sections. The data is processed into a well-structured **pandas DataFrame**, aligned with the schema of [GreekLegalSum](https://huggingface.co/datasets/DominusTea/GreekLegalSum).

---

## 📂 Extracted Data Fields

Each decision includes (at minimum):

- Decision Number & Year
- Section (Civil/Criminal) and Subsection
- Judge Names
- Court Intro
- Legal Reasoning (multiple segments)
- Articles of law (e.g., ΠΚ, ΚΠολΔ, ΑΚ, ΚΠΔ)
- Full Original Text & Cleaned Version
- Source URL

---

## 🛠️ Tools & Libraries

| Category        | Tools Used                         |
|----------------|-------------------------------------|
| Programming     | Python 3.10 / 3.12                  |
| Scraping        | `requests`, `BeautifulSoup`         |
| Text Cleaning   | `regex`, `re`, `pandas`             |
| Visualization   | `matplotlib`, `seaborn`, `wordcloud`|
| Encoding        | Support for `windows-1253`, `utf-8`, `iso-8859-7`|

---

## 📊 Results & Analysis

- **Total Cases Collected:** 2,459 decisions
- **Legal Sections:** ~⅓ Criminal, ~⅔ Civil
- **Most Common Legal References:**
  - Civil: Articles 559, 561, 577 (ΚΠολΔ)
  - Criminal: Several from ΠΚ and ΚΠΔ
- **Visuals Included:**
  - Bar plots, pie charts, section/subsection heatmaps
  - Word clouds of reasoning sections