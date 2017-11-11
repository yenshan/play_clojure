;;
;; https://www.hackerrank.com/challenges/compare-the-triplets/problem
;;
(ns hackerrank.algorithms.warmup.compare-the-triplets
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(def a-score (str->nums (read-line)))
(def b-score (str->nums (read-line)))

(->> (map compare a-score b-score)
     (reduce (fn [res cnd]
               (cond (> cnd 0) (update res :alice inc)
                     (< cnd 0) (update res :bob inc)
                     :else res))
             {:alice 0 :bob 0})
     ((fn [{:keys [alice bob]}] (println alice bob))))

            
