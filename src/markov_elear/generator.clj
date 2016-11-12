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

(defn chain->text [chain])

(defn walk-chain [prefix chain result])

(defn generate-text [prefix chain])

(defn process-file [fname])

(def files ["monad.txt" "quangle-wangle.txt"])

(def data-chain)
