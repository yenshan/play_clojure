;;
;; https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem
;;
(ns hackerrank.algorithms.implementation.climbing-the-leaderboard
  (:require [clojure.string :as s]))

;;
;; GOOD problem: 
;; This prolem set time limitation, it let me think efficient algorithm. 
;; Using map is the most effective solution to pass all test cases in time limitation.
;;

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn check-rank [n coll]
  (let [sz (count coll)]
    (loop [l 0, r (dec sz)]
      (let [p (+ l (quot (- r l) 2))]
        (cond 
          (= l r) (inc l)
          (> n (get coll l)) (inc l)
          (< n (get coll r)) (+ r 2)
          (< n (get coll p)) (recur (inc p) r)
          (> n (get coll p)) (recur l p)
          :else (inc p))))))

(defn rank-history [your-scores rank-scores]
  (let [rkmap (->> (vec (set rank-scores))
                   (sort #(compare %2 %1))
                   (map-indexed vector)
                   (reduce (fn [res [i d]]
                             (assoc res i d))
                           {}))]
    (for [sc your-scores]
      (check-rank sc rkmap))))

(let [_ (read-line)
      rank-scores (str->nums (read-line))
      _ (read-line)
      alice-scores (str->nums (read-line))]
  (doseq [r (rank-history alice-scores rank-scores)]
    (println r)))

