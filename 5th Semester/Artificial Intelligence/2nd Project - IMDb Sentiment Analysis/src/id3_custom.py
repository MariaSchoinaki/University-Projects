import numpy as np
from collections import Counter
import math

class Node:
    def __init__(self, feature=None, threshold=None, left=None, right=None,*,value=None):
        self.feature = feature
        self.threshold = threshold
        self.left = left
        self.right = right
        self.value = value
        
    def is_leaf_node(self):
        return self.value is not None

from statistics import mode

class DecisionTree:
    def __init__(self, features, max_depth = 100, min_samples_split = 2):
        self.features = features
        self.max_depth = max_depth
        self.min_samples_split = min_samples_split
        self.tree = None
        return self.tree

    def fit(self, X, y):
        self.root = self.create_tree(X, y)
        return self.root
    def create_tree(self, X, y, depth=0):
        n_samples, n_feats = X.shape
        n_labels = len(np.unique(y))

        # check the stopping criteria
        if (depth>=self.max_depth or n_labels==1 or n_samples<self.min_samples_split):
            leaf_value = self._most_common_label(y)
            return Node(value=leaf_value)

        # find the best split
        best_feature   = self._best_split(X, y, self.features) 

        # create child nodes
        left_idxs = np.argwhere(X[:, best_feature] <= 0.5).flatten()
        right_idxs = np.argwhere(X[:, best_feature]> 0.5).flatten()
        left = self.create_tree(X[left_idxs, :], y[left_idxs], depth+1)
        right = self.create_tree(X[right_idxs, :], y[right_idxs], depth+1)
        return Node(best_feature, 0.5 ,left, right)


    def _best_split(self, X, y, features):
        best_gain = -1
        split_idx = None

        for feat_idx in features:
            X_column = X[:, feat_idx]
          

            
            gain = self._information_gain(y, X_column, 0.5)

            if gain > best_gain:
                best_gain = gain
                split_idx = feat_idx
                    

        return split_idx


    def _information_gain(self, y, X_column, threshold):

        classes = set(y)

        HC = 0
        for c in classes:
            PC = list(y).count(c) / len(y)  # P(C=c)
            HC += - PC * math.log(PC, 2)  # H(C)
            # print('Overall Entropy:', HC)  # entropy for C variable
            
        feature_values = set(X_column)  # 0 or 1 in this example
        HC_feature = 0
        for value in feature_values:
            # pf --> P(X=x)
            pf = list(X_column).count(value) / len(X_column)  # count occurences of value 
            indices = [i for i in range(len(X_column)) if X_column[i] == value]  # rows (examples) that have X=x

            classes_of_feat = [y[i] for i in indices]  # category of examples listed in indices above
            for c in classes:
                # pcf --> P(C=c|X=x)
                pcf = classes_of_feat.count(c) / len(classes_of_feat)  # given X=x, count C
                if pcf != 0: 
                    # - P(X=x) * P(C=c|X=x) * log2(P(C=c|X=x))
                    temp_H = - pf * pcf * math.log(pcf, 2)
                    # sum for all values of C (class) and X (values of specific feature)
                    HC_feature += temp_H
        
        ig = HC - HC_feature
        return ig    
    
   
    def _entropy(self, y):
        hist = np.bincount(y)
        ps = hist / len(y)
        return -np.sum([p * np.log(p) for p in ps if p>0])

    def predict(self, X):
        return np.array([self._traverse_tree(x, self.root) for x in X])

    def _traverse_tree(self, x, node):
        if node.is_leaf_node():
            return node.value

        if x[node.feature] <= node.threshold:
            return self._traverse_tree(x, node.left)
        return self._traverse_tree(x, node.right)
    
    def _most_common_label(self, y):
        counter = Counter(y)
        value = counter.most_common(1)[0][0]
        return value