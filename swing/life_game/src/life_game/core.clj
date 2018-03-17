(ns life-game.core
  (:import [javax.swing JFrame JPanel Timer]
           [java.awt.event ActionListener])
  (:gen-class))

(def width 40)
(def height 40)

(defn alive? [board pos]
  (some #(= % pos) board))

(def empty-cell? (comp not alive?))

(defn wrap [[x y]]
  [(+ 1 (mod (dec x) width))
   (+ 1 (mod (dec y) height))])

(defn neighbs [[x y]]
  (map wrap 
       (list [(dec x) (dec y)] [x (dec y)] [(inc x) (dec y)]
             [(dec x) y] [(inc x) y]
             [(dec x) (inc y)] [x (inc y)] [(inc x) (inc y)])))

(defn cnt-neighbs [board pos]
  (count (filter #(alive? board %) (neighbs pos))))

(defn survivors [board]
  (->> board
       (filter #(<= 2 (cnt-neighbs board %) 3))))

(defn births [board]
  (->> (for [x (range 1 (inc width)), y (range 1 (inc height))]
            [x y])
       (filter #(and (empty-cell? board %) 
                     (= 3 (cnt-neighbs board %))))))

(defn nextgen [board]
  (concat (survivors board)
          (births board)))

;; --------------------------------------
;; function for drawing
;; --------------------------------------
(def box-size 10)

(def glider [[4 2] [2 3] [4 3] [3 4] [4 4]])

(def board (atom glider))

(defn ap->dp [p]
  (* box-size (dec p)))

(defn draw-cell [g [x y]]
  (let [dx (ap->dp x), dy (ap->dp y)]
    (doto g 
      (.setColor java.awt.Color/BLACK)
      (.fillOval dx dy box-size box-size))))

(def panel 
  (proxy [JPanel ActionListener] []
    (paintComponent [g]
      (doseq [e @board]
        (draw-cell g e))
      (reset! board (nextgen @board))
      )
    (actionPerformed [e]
      (.repaint this))))

(defn -main
  [& args]
  (let [frame (JFrame. "Life Game")
        timer (Timer. 100 panel)]
    (doto frame
      (.add panel)
      (.pack)
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setSize (* width box-size) (* height box-size))
      (.setVisible true))
    (.start timer)))

