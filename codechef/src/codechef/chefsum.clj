(ns codechef.chefsum
  (:require [clojure.test :refer :all]))

;;
;; https://www.codechef.com/problems/CHEFSU://www.codechef.com/problems/CHEFSUM
;;

(defn prefix-sum
  ([i coll] (prefix-sum i coll 0))
  ([i coll res]
   (if (zero? i)
     res
     (recur (dec i) 
            (rest coll)
            (+ res (first coll))))))

(defn sufix-sum 
  ([i coll] (sufix-sum (inc (- (count coll) i)) (vec coll) 0))
  ([i coll res]
   (if (zero? i)
     res
     (recur (dec i)
            (pop coll)
            (+ res (peek coll))))))

(defn prefix-sufix-sum [i coll]
  (+ (prefix-sum i coll) (sufix-sum i coll)))

(defn all-i-sums [coll]
  (for [i (range 1 (inc (count coll)))]
    [i (prefix-sufix-sum i coll)]))


(defn chefsum [coll]
  (letfn [(-compare [a b]
            (if (= (last a) (last b))
              (compare (first a) (first b))
              (compare (last a) (last b))))]
  (->> (all-i-sums coll)
       (sort -compare)
       first first)))
        

(deftest chefsum-test
  (testing "test prefix-sum"
    (is (= 7 (prefix-sum 4 (list 1 1 1 4 2 2))))
    (is (= 1 (prefix-sum 1 (list 1 1 1 4 2 2))))
    (is (= 11 (prefix-sum 6 (list 1 1 1 4 2 2))))
    )
  (testing "test sufix-sum"
    (is (= 9 (sufix-sum 4 (list 1 1 2 3 3 3))))
    (is (= 3 (sufix-sum 6 (list 1 1 2 3 3 3))))
    (is (= 13 (sufix-sum 1 (list 1 1 2 3 3 3))))
    )
  (testing "test chefsum"
    (is (= 1 (chefsum (list 1 2 3))))
    (is (= 2 (chefsum (list 2 1 3 1))))
    )
  )

