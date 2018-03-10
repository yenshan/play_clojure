(ns hackerrank.functional-programming.functional-structures.matrix-rotation
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn edge [f k o mtxm]
  (drop-last 
    (let [elem (filter f mtxm)]
      (condp = o
        :inorder (sort-by k elem)
        :reverse (sort #(compare (get %2 k) (get %1 k)) elem)))))

(defn take-edge [rmin rmax cmin cmax mtxm]
  (let [top-e #(and (= rmin (:r %)) (<= cmin (:c %) cmax))
        right-e #(and (= cmax (:c %)) (<= rmin (:r %) rmax))
        bottom-e #(and (= rmax (:r %)) (<= cmin (:c %) cmax))
        left-e #(and (= cmin (:c %)) (<= rmin (:r %) rmax))
        ]
  (concat (edge top-e :c :inorder mtxm) 
          (edge right-e :r :inorder mtxm)
          (edge bottom-e :c :reverse mtxm)
          (edge left-e :r :reverse mtxm))))

(defn matrix->edgelist [rows cols mtxm]
  (loop [rmin 0
         rmax (dec rows)
         cmin 0
         cmax (dec cols)
         res []]
    (if (or (>= rmin rmax) (>= cmin cmax))
      res
      (recur (inc rmin)
             (dec rmax)
             (inc cmin)
             (dec cmax)
             (conj res (take-edge rmin rmax cmin cmax mtxm))))))

(defn rotate-seq-left [n coll]
  (let [rn (mod n (count coll))]
  (if (zero? rn)
    coll
    (concat (drop rn coll) (take rn coll)))))

(defn update-edge [mtxm coll]
  (map #(assoc %1 :d %2) mtxm coll))

(defn rotate-matrix-left [n rows cols mtxm]
  (->> mtxm
       (matrix->edgelist rows cols)
       (map #(->> (map :d %)
                  (rotate-seq-left n)
                  (update-edge %)))
       (apply concat)))

(defn getd [r c mtxm]
  (some (fn [d] 
          (when (and (= (:r d) r) (= (:c d) c))
           (:d d)))
        mtxm))

(defn print-matrix [rows cols mtxm]
  (doseq [r (range rows)
          c (range cols)]
    (print (getd r c mtxm))
    (if (= c (dec cols))
      (println "")
      (print " "))
    ))

(let [[M N R] (str->nums (read-line))
      mtx-map (apply concat 
                    (for [row (range M)] 
                      (map (fn [[i d]]
                             {:r row :c i :d d })
                           (map-indexed vector (str->nums (read-line))))))
      res (rotate-matrix-left R M N mtx-map)
      ]
  (print-matrix M N res))


