(ns re-frame-todolist.events
  (:require
   [re-frame.core :as re-frame]
   [re-frame-todolist.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

;; step2

(defn- allocate-next-id
  [todos]
  (-> todos
      keys
      last
      ((fnil inc 0))))

(re-frame/reg-event-db
 ::add-todo
 (fn [db [event title]]
   (let [id (allocate-next-id (:todos db))

         ;; step3 項目「completed」追加
         new-todo {:id id :title title :completed false}]

     ;; step3
     ;;(update db :todos #(assoc % id {:id id :title title}))
     (update db :todos #(assoc % id new-todo))
     )))

;; step3

(re-frame/reg-event-db
 ::toggle
 (fn [db [event id]]
   (update-in db [:todos id :completed] not)))

;; step4

(re-frame/reg-event-db
 ::delete
 (fn [db [event id]]
   (update db :todos dissoc id)))

;; step5

(re-frame/reg-event-db
 ::update
 (fn [db [event id title]]
   (let [new-todo {:id id :title title :completed false}]
     (update db :todos #(assoc % id new-todo)))))
