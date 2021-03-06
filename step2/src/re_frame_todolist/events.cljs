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
   (let [id (allocate-next-id (:todos db))]
     (update db :todos #(assoc % id {:id id :title title})))))
