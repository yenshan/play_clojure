(ns codewars.sum-intervals)

(defn sum-intervals
  [intervals]
  (count 
    (reduce (fn [mm [lv rv]]
              (reduce (fn [m x] (assoc m x 1))
                      mm
                      (range lv rv)))
              {}
              intervals)))
               
(sum-intervals [[1 5] [10 15] [-1 3]])
