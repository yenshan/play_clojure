(ns codechef.subseq-eq
  (:require [clojure.test :refer :all]))

;;
;; https://www.codechef.com/problems/LIKECS01
;;

(defn find-char
  "collの中にcを見つける。見つかればc以降のsub sequenceを返す。
  見つからない場合はnilを返す。"
  [c coll]
  (if (empty? coll)
    nil
    (if (= c (first coll))
      coll
      (recur c (rest coll)))))


(defn find-subseq 
  "seq1 seq2の中で同じsub sequenceがあるかどうかを見つける。
  返り値はsub sequence。なければ空リストを返す。
  なお、seq1 seq1は元は同じシーケンスのsub sequceであることを前提にしている。"
  [seq1 seq2]
  (if (or (empty? seq1) (empty? seq2))
    '() 
    (let [c (first seq1)
          ss (find-char c seq2)]
      (cond (not (nil? ss))
              (cons c (find-subseq (rest seq1) (rest ss)))
            (= (count seq1) (inc (count seq2))) ;; seq1とseq1が同じ長さ(同じシーケンス)にならないようにする
              (recur (rest seq1) (rest seq2))
            :else 
              (recur (rest seq1) seq2)))))

(defn has-subseq-eq? [string]
  (not (empty? (find-subseq string (rest string)))))

(deftest subseq-eq-test
  (testing "test has-subseq-eq"
    (is (has-subseq-eq? "xvenivedivici"))
    (is (not (has-subseq-eq? "likecs")))
    (is (not (has-subseq-eq? "bhuvan")))
    (is (has-subseq-eq? "codechef"))
    ))

