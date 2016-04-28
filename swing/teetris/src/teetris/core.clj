(ns teetris.core
  (:import [javax.swing JFrame JPanel Timer]
           [java.awt.event ActionListener KeyListener KeyEvent])
  (:gen-class))

(def BLOCK-SIZE 20)
(def FIELD-WIDTH 10)
(def FIELD-HEIGHT 20)

(def block-type {
                 :I [1 1 1 1
                     0 0 0 0]
                 :O [1 1 0 0
                     1 1 0 0]
                 :S [0 1 1 0
                     1 1 0 0]
                 :Z [1 1 0 0
                     0 1 1 0]
                 :L [1 0 0 0
                     1 1 1 0]
                 :J [0 0 1 0
                     1 1 1 0]
                 :T [1 1 1 0
                     0 1 0 0]
                 })

(def block-table (vals block-type))

(def ppx (atom 5))
(def ppy (atom 0))
(def bti (atom 0))
(def angl (atom 0))


(defn draw-box
  [g x y]
  (doto g 
    (.setColor java.awt.Color/BLACK)
    (.fillRect x y 19 19)))


(defn cal-h
  [i x op] 
  (-> i (mod 4) (* BLOCK-SIZE) (->> (op x))))
(defn cal-h-p [i x] (cal-h i x +))
(defn cal-h-m [i x] (cal-h i x -))

(defn cal-v 
  [i y op]
  (if (< i 4) y (op y BLOCK-SIZE)))
(defn cal-v-p [i y] (cal-v i y +))
(defn cal-v-m [i y] (cal-v i y -))

(defn draw-block
  [g ix iy angl col]
  (let [x (* ix BLOCK-SIZE)
        y (* iy BLOCK-SIZE)]
    (doseq [i (range 0 (count col))]
      (let [e (nth col i)
            opx ({0 cal-h-p, 90 cal-v-p, 180 cal-h-m, 270 cal-v-m} angl)
            opy ({0 cal-v-p, 90 cal-h-m, 180 cal-v-m, 270 cal-h-p} angl)
            px (opx i x)
            py (opy i y)]
        (when (= e 1) (draw-box g px py))))))


(defn main-panel 
  []
  (proxy [JPanel ActionListener] []
    (paintComponent [g]
      (let [block-type (nth block-table @bti)]
        (draw-block g @ppx @ppy @angl block-type)
        (swap! ppy + 1)
        (println @ppy)
        (when (>= @ppy FIELD-HEIGHT) 
          (do (reset! ppy 0)
              (swap! bti #(mod (inc %) 7))))
        ))
    (actionPerformed [e]
      (.repaint this))))

(def klistn
  (proxy [KeyListener] []
    (keyPressed [e]
      (let [kc (.getKeyCode e)]
        (cond (= kc KeyEvent/VK_LEFT) (swap! ppx - 1)
              (= kc KeyEvent/VK_RIGHT) (swap! ppx + 1)
              (= kc KeyEvent/VK_DOWN) (swap! angl #(mod (+ % 90) 360)))))
    (keyReleased [e])
    (keyTyped [e])))

(defn -main
  [& args]
  (let [panel (main-panel) 
        frame (JFrame. "Test Paint")
        timer (Timer. 500 panel)]
    (doto panel
      (.setFocusable true)
      (.addKeyListener klistn))
    (doto frame
      (.add panel)
      (.pack)
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setSize (* FIELD-WIDTH BLOCK-SIZE) (* FIELD-HEIGHT BLOCK-SIZE))
      (.setVisible true))
    (.start timer)))
