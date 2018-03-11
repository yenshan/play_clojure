;;
;; https://www.hackerrank.com/challenges/bomber-man/problem
;;
(ns hackerrank.algorithms.implementation.the-bomberman-game
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))


(defn up [[r c]] [(dec r) c])
(defn down [[r c]] [(inc r) c])
(defn left [[r c]] [r (dec c)])
(defn right [[r c]] [r (inc c)])


(defn plant-bombs [rows cols bombs]
  (loop [[p & rst] (for [r (range rows) c (range cols)] [r c])
         res {}]
    (if (nil? p)
      res
      (recur rst
             (if (get bombs p) res (assoc res p :bmb))))))

(defn detonate [bombs]
  (loop [[[p d :as b] & rst] (vec bombs)
         res {}]
    (if (nil? b)
      res
      (recur rst (-> res
                     (assoc p :bmb)
                     (assoc (up p) :bmb)
                     (assoc (down p) :bmb)
                     (assoc (left p) :bmb)
                     (assoc (right p) :bmb))))))

(defn print-matrix [rows cols bmbm]
  (doseq [r (range rows), c (range cols)]
    (print (if (get bmbm [r c]) \O  \.))
    (when (= c (dec cols)) (println ""))))

(defn print-all-bombs [rows cols]
  (doseq [r (range rows), c (range cols)]
    (print \O)
    (when (= c (dec cols)) (println ""))))

(let [[R C N] (str->nums (read-line))
      bombs (loop [[r & rst] (range R), res {}]
               (if (nil? r)
                 res
                 (let [line (map-indexed vector (read-line))]
                   (recur rst
                          (reduce (fn [rs [i c]]
                                    (if (= c \O)
                                      (assoc rs [r i] :bmb)
                                      rs))
                                  res
                                  line)))))
      ]
  (cond (even? N) (print-all-bombs R C)
        (< N 3) (print-matrix R C bombs)
        :else (let [cnt (count (range 3 (inc N) 2))
                    res1 (plant-bombs R C (detonate bombs))
                    res2 (plant-bombs R C (detonate res1))
                    ]
                (if (odd? cnt)
                  (print-matrix R C res1)
                  (print-matrix R C res2)
                  ))))
