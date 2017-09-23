(ns prog-challenge.p2
  (:require [clojure.test :refer :all]))

;Problem 2
;
; Write a function that combines two lists by alternatingly taking elements. 
; For example: given the two lists [a, b, c] and [1, 2, 3], the function sh-
; ould return [a, 1, b, 2, c, 3].

(defn combine [l1 l2]
  (reduce 
    concat
    []
    (map (fn [a b] [a b]) l1 l2)))

(defn combine2
  ([l1 l2] (combine2 l1 l2 []))
  ([l1 l2 res]
   (if (empty? l1)
     res
     (recur (rest l1) (rest l2) (concat res (list (first l1) (first l2)))))))

(deftest p2-test
  (testing "combine"
    (is (= ['a 1 'b 2 'c 3] (combine ['a 'b 'c] [1 2 3])))
    (is (= ['a 1 'b 2 'c 3] (interleave ['a 'b 'c] [1 2 3])))
    (is (= ['a 1 'b 2 'c 3] (combine2 ['a 'b 'c] [1 2 3])))
    ))
