;;
;; https://www.hackerrank.com/challenges/the-birthday-bar/problem
;;
(ns hackerrank.algorithms.implementation.birthday-chocolate
  (:require [clojure.string :as s]
            [clojure.test :refer :all]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn take-n-sum-eq [n target coll]
  (when (<= n (count coll))
    (= target (reduce + (take n coll)))))


(defn split-pattern [m d coll]
  (loop [_coll coll, res 0]
    (if (> m (count _coll))
      res
      (if (take-n-sum-eq m d _coll)
        (recur (rest _coll) (inc res))
        (recur (rest _coll) res)))))

(def _ (read-line))
(def dat (str->nums (read-line)))

(let [[day month] (str->nums (read-line))]
  (println (split-pattern month day dat)))

;(deftest test-split-pattern
;  (testing "1"
;    (is (= 1 (split-pattern 2 3 [1 2])))
;    (is (= 0 (split-pattern 2 3 [1 1])))
;    (is (= 1 (split-pattern 1 3 [3])))
;    (is (= 1 (split-pattern 1 3 [3 1 2])))
;    (is (= 2 (split-pattern 2 3 [3 1 2 1])))
;    ))

