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


;; m2

(get {:a 1 :b 2} :a)
(get {:a 1 :b 2} :c :not-found)

(first [1 2 3])
(first #{3 2 1})
(last [1 2 3])


(conj [1 2 3] 4)
(conj '(1 2 3) 4)
(conj #{1 2 3} 4)


(rand-nth [1 2 3 4 5 6])
(shuffle [1 2 3 4 5 6])

(defn not-so-rnd [coll] (first coll))

(with-redefs [rand-nth not-so-rnd]
  (rand-nth [1 2 3 4 5 6]))

; lazy seqs

(take 5 (range))

(empty? #{})
(first #{})

