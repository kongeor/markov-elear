(ns markov-elear.generator-test
  (:require [clojure.test :refer :all]
            [markov-elear.generator :refer :all]))

#_(deftest text->words-test
  (testing "text to words"
    (is (= ["And" "the" "Golden" "Grouse" "And" "the" "Pobble" "who"]
           (text->words "And the Golden Grouse And the Pobble who")))))

#_(deftest words->transition-test
  (testing "words to transitions"
    (is (= [["And" "the" "Golden"] ["the" "Golden" "Grouse"] ["Golden" "Grouse"] ["Grouse"]]
           (words->transitions ["And" "the" "Golden" "Grouse"])))))

#_(deftest fmt-trans-test
  (testing "format a transition"
    (is (= {["And" "the"] #{"Pobble"}}
           (fmt-trans ["And" "the" "Pobble"]))))
  #_(testing "format a transition with nils"
    (is (= {["Grouse" nil] #{}}
           (fmt-trans ["Grouse" nil nil])))))


#_(deftest test-word-chain
  (testing "it produces a chain of the possible two step transitions between suffixes and prefixes"
    (let [example '(("And" "the" "Golden")
                    ("the" "Golden" "Grouse")
                    ("And" "the" "Pobble")
                    ("the" "Pobble" "who"))]
      (is (= {["the" "Pobble"] #{"who"}
              ["the" "Golden"] #{"Grouse"}
              ["And" "the"] #{"Pobble" "Golden"}}
             (word-chain example))))))

#_(deftest test-text->word-chain
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
