;;
;; https://www.hackerrank.com/challenges/the-grid-search/problem
;;
(ns hackerrank.algorithms.implementation.the-grid-search
  (:require [clojure.string :as s]))

;;
;; Good problem!!
;; You need consider efficient data process in Clojure to solved this problem in the limited time. (maybe in 3 seconds for test05)
;; Using "clojure.string/index-of" is the best way.
;;

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn read-matrix [r]
  (loop [n r, res []]
    (if (<= n 0)
      res 
      (recur (dec n) 
             (conj res (read-line))))))

(defn all-index-of [col ptn]
  (let [len-col (count col)
        len-ptn (count ptn)]
    (loop [i 0, res []]
      (if (< len-col (+ i len-ptn))
        res
        (if-let [fi (s/index-of col ptn i)]
          (recur (+ fi 1) (conj res fi))
          res)))))

(let [T (Integer/parseInt (read-line))]
  (doseq [i (range T)]
    (let [[R C] (str->nums (read-line))
          matrix (to-array (read-matrix R))
          [r c] (str->nums (read-line))
          pattern (to-array (read-matrix r))
          
          first-ptn (get pattern 0)
          first-ptn-idxs (apply concat 
                                (for [mrow (range R)
                                      :let [line (get matrix mrow)
                                            idxs (all-index-of line first-ptn)
                                            ]
                                      :when (not (empty? idxs))
                                      ]
                                  (map (fn [d] [mrow d]) idxs)
                                  ))
          rest-ptn-res (for [[fr fc] first-ptn-idxs]
                         (for [pi (range 1 r)
                               :let [ptn (get pattern pi)
                                     line (get matrix (+ fr pi))
                                     ptn-c (s/index-of line ptn fc)
                                     ]
                               ]
                           (if (= fc ptn-c)
                             true
                             nil)))
          result (map #(every? true? %) rest-ptn-res)
          ]
      (if (some true? result)
        (println "YES")
        (println "NO"))
     )))
          

