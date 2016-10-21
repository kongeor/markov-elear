(ns markov-elear.generator-test
  (:require [clojure.test :refer :all]
            [markov-elear.generator :refer :all]))

(deftest text->words-test
  (testing "text to words"
    (is (= ["And" "the" "Golden" "Grouse" "And" "the" "Pobble" "who"]
           (text->words "And the Golden Grouse And the Pobble who")))))

(deftest words->transition-test
  (testing "words to transitions"
    (is (= [["And" "the" "Golden"] ["the" "Golden" "Grouse"] ["Golden" "Grouse"] ["Grouse"]]
           (words->transitions ["And" "the" "Golden" "Grouse"])))))

(deftest fmt-trans-test
  (testing "format a transition"
    (is (= {["And" "the"] #{"Pobble"}}
           (fmt-trans ["And" "the" "Pobble"]))))
  (testing "format a transition with nils"
    (is (= {["Grouse" nil] #{}}
           (fmt-trans ["Grouse" nil nil])))))

(deftest test-word-chain
  (testing "it produces a chain of the possible two step transitions between suffixes and prefixes"
    (let [example '(("And" "the" "Golden")
                    ("the" "Golden" "Grouse")
                    ("And" "the" "Pobble")
                    ("the" "Pobble" "who"))]
      (is (= {["the" "Pobble"] #{"who"}
              ["the" "Golden"] #{"Grouse"}
              ["And" "the"] #{"Pobble" "Golden"}}
             (word-chain example))))))

(deftest test-text->word-chain
  (testing "string with spaces and newlines"
    (let [example "And the Golden Grouse\nAnd the Pobble who"]
     (is (= {["who" nil] #{}
             ["Pobble" "who"] #{}
             ["the" "Pobble"] #{"who"}
             ["Grouse" "And"] #{"the"}
             ["Golden" "Grouse"] #{"And"}
             ["the" "Golden"] #{"Grouse"}
             ["And" "the"] #{"Pobble" "Golden"}}
            (text->word-chain example))))))

(deftest test-walk-chain
  (let [chain {["who" nil] #{},
               ["Pobble" "who"] #{},
               ["the" "Pobble"] #{"who"},
               ["Grouse" "And"] #{"the"},
               ["Golden" "Grouse"] #{"And"},
               ["the" "Golden"] #{"Grouse"},
               ["And" "the"] #{"Pobble" "Golden"}}]
    (testing "dead end"
      (let [prefix ["the" "Pobble"]]
        (is (= ["the" "Pobble" "who"]
               (walk-chain prefix chain prefix)))))
    (testing "multiple choices"
      (with-redefs [shuffle (fn [coll] coll)]
        (let [prefix ["And" "the"]]
          (is (= ["And" "the" "Golden"]
                 (walk-chain prefix chain prefix))))))))
