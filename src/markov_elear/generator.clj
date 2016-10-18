(ns markov-elear.generator)

; clojure.string/split

(defn text->words [text])

; partition-all

(defn words->transitions [words])

; http://clojure.org/guides/destructuring

(defn fmt-trans [trans])

; merge-with, clojure.set/union, reduce

(defn word-chain [word-transitions])

; ->

(defn text->word-chain [text])
