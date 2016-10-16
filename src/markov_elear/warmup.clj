(ns markov-elear.warmup)

;; basics

(+ 1 2)

(* (+ 1 2) (- 3 2))


;; if

(if (= 1 2) (+ 1 2) 3)


;; functions

(defn square [x]
  (* x x))

(square 3)


;; seqs, map, reduce

(range 5)

(map square (range 5))

(reduce + (range 5))


;; let

(defn avg [coll]
  (let [sum (reduce + coll)
        cnt (count coll)]
    (/ sum cnt)))

(avg (range 5))


