(ns prog-math-puzzle.q10)

(def europe-style [0,32,15,19,4,21,2,25,17,34,6,27,13,36,11,30,8,23,10,5,24,16,33,1,20,14,31,9,22,18,29,7,28,12,35,3,26])

(def american-style [0,28,9,26,30,11,7,20,32,17,5,22,34,15,3,24,36,13,1,0,27,10,25,29,12,8,19,31,18,6,21,33,16,4,23,35,14,2])

(defn sum [s n array]
  (let [len (count array)]
    (->> (range 0 n)
         (map #(mod (+ s %) len))
         (map #(get array %))
         (apply +))))

(defn max-sum [n array]
  (->> (range 0 (count array))
       (map #(sum % n array))
       (apply max)))

(let [eu-ar (to-array europe-style)
      am-ar (to-array american-style)]
  (println (apply + 
                  (for [n (range 2 37)
                        :when (< (max-sum n eu-ar) (max-sum n am-ar))]
                    1))))

