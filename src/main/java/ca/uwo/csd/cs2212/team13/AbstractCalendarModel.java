package ca.uwo.csd.cs2212.team13;
/**
Copyright 2004 Juan Heyns. All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are
permitted provided that the following conditions are met:

   1. Redistributions of source code must retain the above copyright notice, this list of
      conditions and the following disclaimer.

   2. Redistributions in binary form must reproduce the above copyright notice, this list
      of conditions and the following disclaimer in the documentation and/or other materials
      provided with the distribution.

THIS SOFTWARE IS PROVIDED BY JUAN HEYNS ``AS IS'' AND ANY EXPRESS OR IMPLIED
WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL JUAN HEYNS OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those of the
authors and should not be interpreted as representing official policies, either expressed
or implied, of Juan Heyns.
*/

//test
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created 18 April 2010
 * Updated 26 April 2010
 * 
 * @author Juan Heyns
 *
 * @param <T>
 */
public abstract class AbstractCalendarModel<T> implements CalendarModel<T> {
	public static final String PROPERTY_YEAR = "year";
	public static final String PROPERTY_MONTH = "month";
	public static final String PROPERTY_DAY = "day";
	public static final String PROPERTY_VALUE = "value";
	public static final String PROPERTY_SELECTED = "selected";

	private boolean selected;
	private Calendar calendarValue;
	private HashSet<ChangeListener> changeListeners;
	private HashSet<PropertyChangeListener> propertyChangeListeners;

	protected AbstractCalendarModel() {
		changeListeners = new HashSet<ChangeListener>();	
		propertyChangeListeners = new HashSet<PropertyChangeListener>();
		selected = false;
		calendarValue = Calendar.getInstance();
	}
	
	public synchronized void addChangeListener(ChangeListener changeListener) {
		changeListeners.add(changeListener);
	}

	public synchronized void removeChangeListener(ChangeListener changeListener) {
		changeListeners.remove(changeListener);
	}

	protected synchronized void fireChangeEvent() {
		for (ChangeListener changeListener : changeListeners) {
			changeListener.stateChanged(new ChangeEvent(this));
		}
	}
	
    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeListeners.add(listener);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeListeners.remove(listener);
    }

	protected synchronized void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		if (oldValue != null && newValue != null && oldValue.equals(newValue)) {
		    return;
		}
	
		for (PropertyChangeListener listener : propertyChangeListeners) {
			listener.propertyChange(new PropertyChangeEvent(this, propertyName, oldValue, newValue));
		}
    }

	public int getDay() {
		return calendarValue.get(Calendar.DATE);
	}
	
	public int getMonth() {
		return calendarValue.get(Calendar.MONTH);
	}
	
	public int getYear() {
		return calendarValue.get(Calendar.YEAR);
	}
	
	public T getValue() {
		if (!selected) {
			return null;
		}
		T value = fromCalendar(calendarValue);
		
		return value;
	}
	
	public Date getValueDate() {
		if (!selected) {
			return null;
		}
		Date value = calendarValue.getTime();
		
		return value;
	}
	
	public void setDay(int day) {
		int oldDayValue = this.calendarValue.get(Calendar.DATE);
		T oldValue = getValue();
		calendarValue.set(Calendar.DATE, day);
		fireChangeEvent();
		firePropertyChange(PROPERTY_DAY, oldDayValue, this.calendarValue.get(Calendar.DATE));
		firePropertyChange(PROPERTY_VALUE, oldValue, getValue());
	}

	public void addDay(int add) {
		int oldDayValue = this.calendarValue.get(Calendar.DATE);
		T oldValue = getValue();
		calendarValue.add(Calendar.DATE, add);
		fireChangeEvent();
		firePropertyChange(PROPERTY_DAY, oldDayValue, this.calendarValue.get(Calendar.DATE));
		firePropertyChange(PROPERTY_VALUE, oldValue, getValue());
	}
	
	public void setMonth(int month) {
		int oldMonthValue = this.calendarValue.get(Calendar.MONTH);
		T oldValue = getValue();
		calendarValue.set(Calendar.MONTH, month);
		fireChangeEvent();
		firePropertyChange(PROPERTY_MONTH, oldMonthValue, this.calendarValue.get(Calendar.MONTH));
		firePropertyChange(PROPERTY_VALUE, oldValue, getValue());
	}

	public void addMonth(int add) {
		int oldMonthValue = this.calendarValue.get(Calendar.MONTH);
		T oldValue = getValue();
		calendarValue.add(Calendar.MONTH, add);
		fireChangeEvent();
		firePropertyChange(PROPERTY_MONTH, oldMonthValue, this.calendarValue.get(Calendar.MONTH));
		firePropertyChange(PROPERTY_VALUE, oldValue, getValue());
	}
	
	public void setYear(int year) {
		int oldYearValue = this.calendarValue.get(Calendar.YEAR);
		T oldValue = getValue();
		calendarValue.set(Calendar.YEAR, year);
		fireChangeEvent();
		firePropertyChange(PROPERTY_YEAR, oldYearValue, this.calendarValue.get(Calendar.YEAR));
		firePropertyChange(PROPERTY_VALUE, oldValue, getValue());
	}

	public void addYear(int add) {
		int oldYearValue = this.calendarValue.get(Calendar.YEAR);
		T oldValue = getValue();
		calendarValue.add(Calendar.YEAR, add);
		fireChangeEvent();
		firePropertyChange(PROPERTY_YEAR, oldYearValue, this.calendarValue.get(Calendar.YEAR));
		firePropertyChange(PROPERTY_VALUE, oldValue, getValue());
	}
	
	public void setValue(T value) {
		int oldYearValue = this.calendarValue.get(Calendar.YEAR);
		int oldMonthValue = this.calendarValue.get(Calendar.MONTH);
		int oldDayValue = this.calendarValue.get(Calendar.DATE);
		T oldValue = getValue();
		boolean oldSelectedValue = isSelected();
		
		if (value != null) {
			this.calendarValue = toCalendar(value);
			setToMidnight();
			selected = true;
		}
		else {
			selected = false;
		}
		
		fireChangeEvent();
		firePropertyChange(PROPERTY_YEAR, oldYearValue, this.calendarValue.get(Calendar.YEAR));
		firePropertyChange(PROPERTY_MONTH, oldMonthValue, this.calendarValue.get(Calendar.MONTH));
		firePropertyChange(PROPERTY_DAY, oldDayValue, this.calendarValue.get(Calendar.DATE));
		firePropertyChange(PROPERTY_VALUE, oldValue, getValue());
		firePropertyChange("selected", oldSelectedValue, this.selected);
	}
	
	public void setDate(int year, int month, int day) {
		int oldYearValue = this.calendarValue.get(Calendar.YEAR);
		int oldMonthValue = this.calendarValue.get(Calendar.MONTH);
		int oldDayValue = this.calendarValue.get(Calendar.DATE);
		T oldValue = getValue();
		calendarValue.set(year, month, day);
		fireChangeEvent();
		firePropertyChange(PROPERTY_YEAR, oldYearValue, this.calendarValue.get(Calendar.YEAR));
		firePropertyChange(PROPERTY_MONTH, oldMonthValue, this.calendarValue.get(Calendar.MONTH));
		firePropertyChange(PROPERTY_DAY, oldDayValue, this.calendarValue.get(Calendar.DATE));
		firePropertyChange(PROPERTY_VALUE, oldValue, getValue());
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		T oldValue = getValue();
		boolean oldSelectedValue = isSelected();
		this.selected = selected; 
		fireChangeEvent();
		firePropertyChange(PROPERTY_VALUE, oldValue, getValue());
		firePropertyChange(PROPERTY_SELECTED, oldSelectedValue, this.selected);
	}

	private void setToMidnight() {
		calendarValue.set(Calendar.HOUR, 0);
		calendarValue.set(Calendar.MINUTE, 0);
		calendarValue.set(Calendar.SECOND, 0);
		calendarValue.set(Calendar.MILLISECOND, 0);
	}

	protected abstract Calendar toCalendar(T from);
	
	protected abstract T fromCalendar(Calendar from);

}