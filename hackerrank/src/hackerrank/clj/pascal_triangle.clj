;;
;;https://www.hackerrank.com/challenges/pascals-triangle/problem
;;
(ns hackerrank.clj.pascal-triangle)

(defn add-one-both-side [coll]
  (concat [1] coll [1]))

(defn next-row [coll]
  (->> coll
       (partition 2 1)
       (map (fn [[a b]] (+ a b)))
       (add-one-both-side)))

(defn pascal-triangle
  ([] (pascal-triangle [1]))
  ([coll]
   (cons coll (lazy-seq (pascal-triangle (next-row coll))))))

(defn print-row [coll]
  (doseq [i coll]
    (print (str i " ")))
  (print "\n"))

(let [K (Integer/parseInt (read-line))]
  (doseq [e (take K (pascal-triangle))]
    (print-row e)))
