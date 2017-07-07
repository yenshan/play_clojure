(ns pong.core
  (:import [javax.swing JFrame JPanel Timer]
           [java.awt.event ActionListener KeyListener KeyEvent]
           [java.awt Color])
  (:gen-class))


(defn create-ball [x y]
  {:x x :y y 
   :w 10 :h 10
   :vx 1 :vy 1
   :color Color/BLACK
   :draw #(.fillOval %1 %2 %3 %4 %5)
   })

(def ball (atom (create-ball 10 10)))

(defn create-bar [x y]
  {:x x :y y
   :w 50 :h 10
   :color Color/BLUE
   :draw #(.fillRect %1 %2 %3 %4 %5)
   :ref-v :vy
   })

(def bar (atom (create-bar 200 300)))

(defrecord Wall [x y w h ref-v])

(def walls [(Wall. 0 0 1 100 :vx)
            (Wall. 390 0 1 400 :vx)
            (Wall. 0 0 400 1 :vy)
            (Wall. 0 390 400 1 :vy)])

(defn move-by-velocity
  [{:keys [x y vx vy] :as obj}]
  (assoc obj :x (+ x vx) :y (+ y vy)))


(defn rect-hit? [[tx1 ty1 bx1 by1] [tx2 ty2 bx2 by2]]
  (not (or (< bx1 tx2) (< by1 ty2) (< bx2 tx1) (< by2 ty1))))

(defn hit?
  [obj1 obj2]
  (letfn [(->pos [{:keys [x y w h]}] [x y (+ x w) (+ y h)])]
    (rect-hit? (->pos obj1) (->pos obj2))))


(defn turn-if-hit
  [bal bar]
  (let [vt (:ref-v bar)
        v (get bal vt)]
    (if (hit? bal bar)
      (assoc bal vt (- v))
      bal)))

(defn move-ball!
  [bal]
  (let [objs (cons @bar walls)
        collision-check (partial reduce turn-if-hit)]
    (reset! ball (-> @ball 
                     (move-by-velocity)
                     (collision-check objs)
                     ))))

(defn move-bar!
  [dat dr]
  (let [d (dr {:left - :right +})
        x (:x @dat)]
    (->> (d x 5)
         (assoc @dat :x)
         (reset! dat))))
      
(defn draw
  [g {:keys [x y w h color draw]}]
      (.setColor g color)
      (draw g x y w h))

(defn main-panel 
  []
  (proxy [JPanel ActionListener] []
    (paintComponent [g]
      (let [w (proxy-super getWidth)
            h (proxy-super getHeight)]
        (move-ball! ball)
        (proxy-super paintComponent  g)
        (draw g @ball)
        (draw g @bar)
        ))
    (actionPerformed [e]
      (.repaint this))))

(def klistn
  (proxy [KeyListener] []
    (keyPressed [e]
      (let [kc (.getKeyCode e)]
        (cond (= kc KeyEvent/VK_LEFT) (move-bar! bar :left)
              (= kc KeyEvent/VK_RIGHT) (move-bar! bar :right))))
    (keyReleased [e])
    (keyTyped [e])))

(defn -main
  [& args]
  (let [panel (main-panel) 
        timer (Timer. 10 panel)]
    (doto panel
      (.setFocusable true)
      (.addKeyListener klistn))
    (doto (JFrame. "Test Paint")
      (.add panel)
      (.pack)
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setSize 400 400)
      (.setVisible true))
    (.start timer)))

