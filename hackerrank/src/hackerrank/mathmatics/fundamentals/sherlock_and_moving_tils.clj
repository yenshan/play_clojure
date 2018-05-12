;;
;; https://www.hackerrank.com/challenges/sherlock-and-moving-tiles/problem
;;
(ns hackerrank.mathmatics.fundamentals.sherlock-and-moving-tils
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn square [x] (* x x))

(defn cal-time [l s1 s2 q]
  (/ (Math/sqrt  
       (* 2 (square (- l
                       (Math/sqrt q)))))
     (Math/abs (- s2 s1))))


(let [[L S1 S2] (str->nums (read-line))
      Q (Integer/parseInt (read-line))]
  (doseq [_ (range Q)]
    (let [q (read-string (read-line))]
      (println (cal-time L S1 S2 q)))))

