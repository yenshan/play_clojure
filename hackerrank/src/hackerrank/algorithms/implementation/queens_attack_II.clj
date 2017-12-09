;;
;; https://www.hackerrank.com/challenges/queens-attack-2/problem
;;
(ns hackerrank.algorithms.implementation.queens-attack-II
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn check-queen-way [f pos n objstacle]
  (loop [[r c :as p] (f pos), res []]
    (cond (or (< r 1) (> r n)) res
          (or (< c 1) (> c n)) res
          (objstacle p) res
          :else (recur (f p)
                       (conj res p)))))
               

(let [[n k] (str->nums (read-line))
      queen-pos (str->nums (read-line))
      objstacles (->> (for [_ (range k)]
                      (str->nums (read-line)))
                    (reduce (fn [res d]
                              (assoc res d 1))
                            {}))]
  (letfn [(check-queen-way [f]
            (loop [[r c :as p] (f queen-pos), res []]
              (cond (or (< r 1) (> r n)) res
                    (or (< c 1) (> c n)) res
                    (objstacles p) res
                    :else (recur (f p)
                                 (conj res p)))))]
    (let [cell-pos (concat 
                     (check-queen-way (fn [[r c]] [(inc r) c]))
                     (check-queen-way (fn [[r c]] [(dec r) c]))
                     (check-queen-way (fn [[r c]] [r (inc c)]))
                     (check-queen-way (fn [[r c]] [r (dec c)]))
                     (check-queen-way (fn [[r c]] [(inc r) (inc c)]))
                     (check-queen-way (fn [[r c]] [(dec r) (dec c)]))
                     (check-queen-way (fn [[r c]] [(inc r) (dec c)]))
                     (check-queen-way (fn [[r c]] [(dec r) (inc c)]))
                     )
          ]
      (println (count cell-pos))
      )))
