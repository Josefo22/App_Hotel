package com.example.hotelapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hotelapp.R
import com.example.hotelapp.databinding.FragmentCalendarBinding
import com.example.hotelapp.ui.viewmodels.ReservationViewModel
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import java.util.Date
import java.time.ZoneId

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    
    private val reservationViewModel: ReservationViewModel by viewModels()
    private var selectedDate: LocalDate? = null
    private val today = LocalDate.now()
    
    private val daysOfWeekFormatter = DateTimeFormatter.ofPattern("EEE", Locale.getDefault())
    private val monthTitleFormatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupCalendar()
        setupButtons()
        loadReservations()
    }
    
    private fun setupCalendar() {
        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(1)
        val endMonth = currentMonth.plusMonths(6)
        
        binding.calendarView.apply {
            setup(startMonth, endMonth, daysOfWeek().first())
            scrollToMonth(currentMonth)
            
            dayBinder = object : DayBinder<DayViewContainer> {
                override fun create(view: View) = DayViewContainer(view)
                
                override fun bind(container: DayViewContainer, day: CalendarDay) {
                    container.day = day
                    val textView = container.textView
                    textView.text = day.date.dayOfMonth.toString()
                    
                    if (day.owner == DayOwner.THIS_MONTH) {
                        textView.visibility = View.VISIBLE
                        
                        when {
                            selectedDate == day.date -> {
                                textView.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
                                textView.setBackgroundResource(R.drawable.calendar_selected_bg)
                            }
                            today == day.date -> {
                                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
                                textView.setBackgroundResource(R.drawable.calendar_today_bg)
                            }
                            else -> {
                                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorOnSurface))
                                textView.background = null
                            }
                        }
                    } else {
                        textView.visibility = View.INVISIBLE
                    }
                }
            }
            
            monthHeaderBinder = object : MonthHeaderFooterBinder<MonthViewContainer> {
                override fun create(view: View) = MonthViewContainer(view)
                
                override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                    container.textView.text = monthTitleFormatter.format(month.yearMonth)
                }
            }
            
            monthScrollListener = { month ->
                binding.monthTitle.text = monthTitleFormatter.format(month.yearMonth)
            }
        }
        
        binding.calendarView.monthScrollListener = { month ->
            binding.monthTitle.text = monthTitleFormatter.format(month.yearMonth)
        }
    }
    
    private fun daysOfWeek(): List<java.time.DayOfWeek> {
        return listOf(
            java.time.DayOfWeek.MONDAY,
            java.time.DayOfWeek.TUESDAY, 
            java.time.DayOfWeek.WEDNESDAY,
            java.time.DayOfWeek.THURSDAY,
            java.time.DayOfWeek.FRIDAY,
            java.time.DayOfWeek.SATURDAY,
            java.time.DayOfWeek.SUNDAY
        )
    }
    
    private fun setupButtons() {
        binding.btnPrevMonth.setOnClickListener {
            binding.calendarView.findFirstVisibleMonth()?.let {
                binding.calendarView.smoothScrollToMonth(it.yearMonth.minusMonths(1))
            }
        }
        
        binding.btnNextMonth.setOnClickListener {
            binding.calendarView.findFirstVisibleMonth()?.let {
                binding.calendarView.smoothScrollToMonth(it.yearMonth.plusMonths(1))
            }
        }
        
        binding.btnCreateReservation.setOnClickListener {
            if (selectedDate != null) {
                val action = CalendarFragmentDirections.actionCalendarFragmentToCreateReservationFragment(
                    roomId = -1L,
                    customerId = -1L
                )
                findNavController().navigate(action)
            } else {
                // Mostrar mensaje para seleccionar una fecha
                binding.tvCalendarInfo.text = getString(R.string.please_select_date)
                binding.tvCalendarInfo.visibility = View.VISIBLE
            }
        }
    }
    
    private fun loadReservations() {
        reservationViewModel.allReservations.observe(viewLifecycleOwner) { reservations ->
            if (reservations.isEmpty()) {
                binding.tvCalendarInfo.text = getString(R.string.no_reservations)
                binding.tvCalendarInfo.visibility = View.VISIBLE
            } else {
                binding.tvCalendarInfo.visibility = View.GONE
                // Refrescar el calendario con las reservaciones
                binding.calendarView.notifyCalendarChanged()
            }
        }
    }
    
    inner class DayViewContainer(view: View) : ViewContainer(view) {
        val textView = view.findViewById<android.widget.TextView>(R.id.calendarDayText)
        var day: CalendarDay? = null
        
        init {
            view.setOnClickListener {
                day?.let {
                    if (it.owner == DayOwner.THIS_MONTH) {
                        val currentSelection = selectedDate
                        if (currentSelection == it.date) {
                            selectedDate = null
                            binding.calendarView.notifyDayChanged(it)
                        } else {
                            selectedDate = it.date
                            binding.calendarView.notifyDayChanged(it)
                            if (currentSelection != null) {
                                binding.calendarView.notifyDateChanged(currentSelection)
                            }
                            
                            // Mostrar reservaciones para esta fecha
                            showReservationsForDate(it.date)
                        }
                    }
                }
            }
        }
    }
    
    inner class MonthViewContainer(view: View) : ViewContainer(view) {
        val textView = view.findViewById<android.widget.TextView>(R.id.calendarMonthText)
    }
    
    private fun showReservationsForDate(date: LocalDate) {
        // Convertir LocalDate a Date para usar con el método getReservationsByDateRange
        val startDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())
        val endDate = Date.from(date.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant())
        
        reservationViewModel.getReservationsByDateRange(startDate, endDate).observe(viewLifecycleOwner) { reservations ->
            if (reservations.isEmpty()) {
                binding.tvCalendarInfo.text = getString(R.string.no_reservations_for_date, date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                binding.tvCalendarInfo.visibility = View.VISIBLE
                binding.rvDateReservations.visibility = View.GONE
            } else {
                binding.tvCalendarInfo.visibility = View.GONE
                binding.rvDateReservations.visibility = View.VISIBLE
                // Aquí adaptaríamos un RecyclerView con las reservaciones para esa fecha
                // Esto requeriría un adapter adicional para mostrar las reservaciones del día
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 