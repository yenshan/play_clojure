(require '[clojure.string :as str])

(defn split-by-center
  ([coll] (split-by-center coll '()))
  ([coll res]
   (let [e1 (first coll)
         e2 (second coll)]
     (cond (or (= e1 e2) (= e2 (inc e1)))
           (recur (rest coll)
                  (concat res (list e1)))
           (> e1 e2)
           [res (rest coll)]
           :else
           ['() coll]))))

(defn rainbow-array? [coll]
  (let [tmp (split-by-center coll)]
    (= (first tmp)
       (reverse (second tmp)))))

(defn string->num-array [string]
  (map read-string (str/split string #" ")))

; your code goes here
(let [n (read-string (read-line))]
  (loop [i 0]
    (when (< i n)
      (let [_ (read-line)
            numa (string->num-array (read-line))]
        (println (if (rainbow-array? numa) "yes" "no")))
      (recur (inc i)))))

