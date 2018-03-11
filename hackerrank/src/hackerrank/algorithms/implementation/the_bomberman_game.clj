(ns hackerrank.algorithms.implementation.the-bomberman-game
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn print-matrix [mtxm rows cols]
  (doseq [r (range rows), c (range cols)]
    (print (if (= nil (get mtxm [r c]))
             \. \O))
    (when (= c (dec cols)) (println ""))))


(defn plant-bombs [mtxm bmb]
  (reduce (fn [res d]
            (if (= (get res d) nil)
              (assoc res d bmb)
              res))
          mtxm
          (keys mtxm)))

(defn up [[r c]] [(dec r) c])
(defn down [[r c]] [(inc r) c])
(defn left [[r c]] [r (dec c)])
(defn right [[r c]] [r (inc c)])

(defn detonate [mtxm pos]
  (reduce (fn [res d]
            (if (or (= d pos)
                    (= d (up pos))
                    (= d (down pos))
                    (= d (left pos))
                    (= d (right pos))) (assoc res d nil)
              res))
          mtxm
          (keys mtxm)))

(defn detonate-all [mtxm bmb]
  (let [bmb-pos (filter (fn [[_ b]] (= bmb b)) mtxm)]
    (reduce (fn [res d]
              (detonate res d))
            mtxm
            (keys bmb-pos))))

(defn next-bmb-type [bt]
  (if (= bt :bmb1) :bmb1 :bmb2))

(defn plant-detonate-loop [mtxm n]
  (loop [cnt 1
         res mtxm
         plant-bmb :bmb2
         deto-bmb :bmb1
         ]
    (cond (> cnt n) res
          (odd? cnt) (recur (inc cnt) 
                            (plant-bombs res plant-bmb)
                            (next-bmb-type plant-bmb)
                            deto-bmb)
          :else (recur (inc cnt)
                       (detonate-all res deto-bmb)
                       plant-bmb
                       (next-bmb-type deto-bmb)))))


(let [[R C N] (str->nums (read-line))
      matrix (loop [[r & rst] (range R), res {}]
               (if (nil? r)
                 res
                 (let [line (map-indexed vector (read-line))]
                   (recur rst
                          (reduce (fn [rs [i c]]
                                    (assoc rs [r i] (if (= c \O) :bmb1 nil)))
                                  res
                                  line)))))
      res (plant-detonate-loop matrix (dec N))
      ]
  (print-matrix res R C))

