package com.twere.android.clean.way.rx

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import rx.Observable
import rx.subjects.BehaviorSubject
import rx.subjects.PublishSubject

object RxEvents {
  fun text(editText: EditText): Observable<String> {
    val behaviourSubject = BehaviorSubject.create(editText.text.toString())
    editText.addTextChangedListener(object : TextWatcher {
      override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
      }

      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
      }

      override fun afterTextChanged(s: Editable) {
        behaviourSubject.onNext(s.toString())
      }
    })
    return behaviourSubject
  }

  fun click(view: View): Observable<Any> {
    val subject: PublishSubject<Any> = PublishSubject.create()
    view.setOnClickListener({ subject.onNext(Any()) })
    return subject
  }

  // TODO: Add more events
}

