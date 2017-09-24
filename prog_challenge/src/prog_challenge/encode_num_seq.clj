(ns prog-challenge.encode-num-seq
  (:require [clojure.test :refer :all]))

;; m (m >= 0) から始まる整数列 m, m + 1, m + 2, … の N 進表記を遅延シーケンス
;; (lazy sequence) として返す関数 encoded-num-seq を書け． 
;; ただし，N 進表記のために 0, 1, …, N - 1 に対応する記号列が文字列 digits と
;; して渡されるので，この記号を用いた N 進表記を返すこと．N は digits の文字長である．
;;
;; 例:
;;
;;  (nth (encoded-num-seq 0 "ABC") 10000000)
;;  → "CAACBBAABBACBAB"
;;
;; 例の解説:
;;   記号列 “"ABC” の長さが 3 なので，(encoded-num-seq 0 “"ABC”) は， 0, 1, 2, … を
;; 3 進数で表記した遅延シーケンスを返す． この遅延シーケンスに対して (nth s 10000000) は，
;; 一千万番目のものを返すから， 9999999 の 3 進表記 200211001102101 を “ABC” を記号列と
;; して表記しなおした， “CAACBBAABBACBAB” が返っている．

(defn decimal->n-base-num
  ([base num] (if (zero? num)
                [0] 
                (decimal->n-base-num base num [])))
  ([base num res]
   (if (zero? num) 
     res
     (recur base
            (quot num base)
            (cons (rem num base) res)))))

(defn encode [num syms]
  (->>
    (decimal->n-base-num (count syms) num)
    (map #(nth syms %))
    (apply str)))

(defn encode-num-seq
  [n syms]
  (map #(encode % syms) (iterate inc n)))


(deftest test-encode-num-seq
  (testing "test decimal->n-base-num"
    (is (= [0] (decimal->n-base-num 3 0)))
    (is (= [1 0 1] (decimal->n-base-num 3 10)))
    )
  (testing "test 1"
    (is (= '("A" "B" "C" "BA" "BB" "BC" "CA" "CB" "CC" "BAA")
           (take 10 (encode-num-seq 0 "ABC"))))
    )
  (testing "test 2"
    (is (= "CAACBBAABBACBAB" (nth (encode-num-seq 0 "ABC") 10000000)))
  )
)


