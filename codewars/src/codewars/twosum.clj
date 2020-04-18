(ns codewars.twosum)


(defn perm [[x & xs]]
  (if (nil? x)
    []
    (concat (for [y xs] [x y])
          (perm xs))))

(defn twosum [numbers target]
  (->> numbers
       (map-indexed vector)
       perm
       (filter (fn [[[_ a] [_ b]]]
                   (= (+ a b) target)))
       first
       ((fn [[[a _] [b _]]] [a b]))))


(twosum [1 2 3] 4)
