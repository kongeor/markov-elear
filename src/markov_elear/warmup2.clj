(ns markov-elear.warmup2
  (:require [clojure.set :refer [union]]
            [clojure.java.io :refer [resource]])
  (:import java.util.Random))

(. (new Random) nextInt)

(union #{1} #{2})

;; recur

(let [a 1
      b 2]
  (+ a b))

(loop [coll [1 2 3 4 5]
       i 0]
  (if (not= i (count coll))
    (do
      (println (nth coll i))
      (recur coll (inc i)))))

(loop [coll (range 1 6)]
  (if coll
    (do (println (first coll))
        (recur (next coll)))))

(defn fact [n]
  (loop [n n
         acc 1]
    (if (= n 0)
      acc
      (recur (dec n) (* acc n)))))

(defn fact2 [n]
  (reduce * (range 1 (inc n))))

(fact2 5)

(do
  (doall (map println [1 2 3 4 5]))
  nil)

(defn rev [coll]
  (loop [coll coll
         rev []]
    (if coll
      (recur  (butlast coll) (conj rev (last coll)))
      rev)))

(rev (range 5))

;; implement shuffle

;; with-redefs

(clojure.string/join (shuffle (seq "asdf")))

(identity [1 2 3])

(group-by identity [1 1 1 2 2 3])

(group-by odd? [1 1 1 2 2 3])

(frequencies [1 1 1 2 2 3])

(with-redefs [shuffle identity]
  (shuffle [1 2 3 4 5]))

;; for

(for [x (range 5)
      :when (odd? x)
      y (range 5)
      :when (> x y)]
  [x y])

;; interpose, interleave

(interpose "-" [1 2 3 4])

(interleave (cycle [1 2]) [:a :b :c :d :e])

;; cycle

(take 6 (drop 2 (cycle [1 2 3 4 5 6])))

;; ns, requires, tests

(== 0 0.0)

(defn == [a b])


;; slurp

(slurp (resource "monad.txt"))
(slurp "http://google.com")

;; apply

(+ 1 2 3)

(defn add [x & xs]
  (println x)
  (apply + x xs))

(add 1 2 3)

(apply add (range 5))

(apply + 1 [2 3 4])

;; merge-with

(merge-with set {:a 1 :c 20} {:a 2} {:b 2} {:b 5} {:c 20})


#{1} ;set
#"\n" ;regex
#_(+ 1 2) ;comment
#() ; anonymous func

( (fn [a b] (+ 1 2)) 1 2)

(#(+ % %2) 1 2)
