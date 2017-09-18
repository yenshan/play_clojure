(ns cljap.compare)

(defn compare-author1 [s1 s2]
  (let [c (compare (:lname s1) (:lname s2))]
    (if (zero? c)
      (compare (:fname s1) (:fname s2))
      c)))

(defn compare-author2 [s1 s2]
  (let [project-author (juxt :lname :fname)]
    (compare (project-author s1) (project-author s2))))

