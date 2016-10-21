(ns markov-elear.generator
  (:require [clojure.string :as str]
            [clojure.set :refer [union]]))

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

(defn walk-chain [prefix chain result]
  (let [words (get chain prefix)
        word (first words)]
    (conj result word)))
