(ns hackerrank.clj.recursive-tree)

(defn make-branch
  ([f p len] (make-branch f p len []))
  ([f p len res]
   (if (zero? len)
     res
     (recur f 
            (f p)
            (dec len)
            (conj res p)))))

(defn make-ver-bar [p len]
 (make-branch (fn [p] [(first p) (- (second p) 1)])
              p len))

(defn make-left-branch [p len]
 (make-branch (fn [p] [(dec (first p)) (- (second p) 1)])
              p len))

(defn make-right-branch [p len]
 (make-branch (fn [p] [(inc (first p)) (- (second p) 1)])
              p len))


(defn make-y-tree [p len]
  (list (make-ver-bar p len)
        (make-left-branch [(dec (first p)) (- (second p) len)] len)
        (make-right-branch [(inc (first p)) (- (second p) len)] len)))

(defn recur-y-tree [p len n]
  (let [tree (make-y-tree p len)]
    (if (<= n 1)
      tree
      (let [lp (last (second tree))
            rp (last (nth tree 2))]
        (concat tree
                (recur-y-tree [(first lp) (dec (second lp))]
                              (quot len 2)
                              (dec n))
                (recur-y-tree [(first rp) (dec (second rp))]
                              (quot len 2)
                              (dec n)))))))

(defn draw-area
  "座標は[0 0]から幅w、高さhの領域の描画を行う。"
  [w h coll]
    (doseq [iy (range h)
            ix (range w)]
      (when (and (> iy 0) (zero? ix)) (print "\n"))
      (if (some #(= % [ix iy]) coll)
        (print \1)
        (print \_))))


(->> (Integer/parseInt (read-line))
     (recur-y-tree [49 62] 16)
     (apply concat)
     (draw-area 100 63))

