(ns markov-elear.generator
  (:require [clojure.string :as str]
            [clojure.set :refer [union]]))

;; ========
;; m1

(defn text->words [text]
  (str/split text #"[\n|\s]"))

(defn words->transitions [words]
  (partition-all 3 1 words))

(defn fmt-trans [[a b c]]
  {[a b] (if c #{c} #{})})

(defn word-chain [word-transitions]
  (reduce #(merge-with union % (fmt-trans %2)) {} word-transitions))

(defn text->word-chain [text]
  (-> text text->words words->transitions word-chain))

;; ========
;; m2

(defn chain->text [chain]
  (apply str (interpose " " chain)))

(defn walk-chain [prefix chain result]
  (let [suffixes (get chain prefix)]
    (if (empty? suffixes)
      result
      (let [suffix (first (shuffle suffixes))
            new-prefix [(second prefix) suffix]
            result-char-count (count (chain->text result))
            suffix-char-count (inc (count suffix))          ;; notice inc
            total-count-char (+ result-char-count suffix-char-count)]
        (if (>= total-count-char 140)
          result
          (recur new-prefix chain (conj result suffix)))))))

(defn generate-text [prefix chain]
  (let [prefix (text->words prefix)]
    (chain->text (walk-chain prefix chain prefix))))

(defn process-file [fname]
  (text->word-chain
    (slurp (clojure.java.io/resource fname))))

(def files ["monad.txt" "quangle-wangle.txt"])

(def data-chain (apply merge-with clojure.set/union (map process-file files)))
