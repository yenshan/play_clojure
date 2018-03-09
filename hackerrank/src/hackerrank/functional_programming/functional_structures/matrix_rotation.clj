(ns hackerrank.functional-programming.functional-structures.matrix-rotation
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))


(defn rotate [{:keys [r c] :as pos} r-min r-max c-min c-max]
  (cond-> pos
    (and (= c c-min) (>= r r-min) (<= r (dec r-max))) (update :r inc)
    (and (= c c-max) (>= r (inc r-min)) (<= r r-max)) (update :r dec)
    (and (= r r-min) (>= c (inc c-min)) (<= c c-max)) (update :c dec)
    (and (= r r-max) (>= c c-min) (<= c (dec c-max))) (update :c inc)))

(defn rotate-matrix [mtxm rows cols]
  (loop [r-min 0
         r-max (dec rows)
         c-min 0
         c-max (dec cols)
         res mtxm]
    (if (or (>= r-min r-max) (>= c-min c-max))
      res
      (let [nmtx (map #(rotate % r-min r-max c-min c-max) res)]
        (recur (inc r-min)
               (dec r-max)
               (inc c-min)
               (dec c-max)
               nmtx)))))

(defn get-n [mtxm row col]
  (:d (some (fn [{:keys [r c] :as dat}]
                (when (and (= r row) (= c col))
                  dat))
            mtxm)))

(defn matrix-map->matrix [mtxm m n]
  (for [r (range m)]
    (for [c (range n)]
      (get-n mtxm r c))))

(defn print-matrix [mtx]
  (letfn [(trim [s] (s/replace s #"[\[\]]" ""))]
    (doseq [row mtx]
      (println (trim (str (vec row)))))))

(let [[M N R] (str->nums (read-line))
      mtx-map (apply concat 
                    (for [row (range M)] 
                      (map (fn [[i d]]
                             {:r row :c i :d d })
                           (map-indexed vector (str->nums (read-line))))))
      res (reduce (fn [m _] (rotate-matrix m M N)) mtx-map (range R))
      ]
  (print-matrix (matrix-map->matrix res M N)))

