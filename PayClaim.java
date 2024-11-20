import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.YearMonth;

/*

    * getSecondFriday() class completed
    * ableToClaim and payClaimed can be introduced as fields in Employees class, and can
    be replaced by getAbleToClaim(Employee e) once introduced
    * "pay employee" can be replaced with a call of the Payslip class once completed
    * printlns can be seen in the CLI



@author Liam Finn
@date 7.11.2024
 */
public class PayClaim {

    Employee e = new Employee();
    public static void validateClaim(Employee e) {
        LocalDate today = LocalDate.now(); //day employee submits claim

        YearMonth thisMonth = YearMonth.now();
        LocalDate secondFriday = getSecondFriday(thisMonth);

        if(e.ableToClaim){ //Automatically true. Can be changed from firing etc.

            if(e.payClaimed){ //pay already claimed this month
                System.out.println("Pay already claimed");
            } if (!e.payClaimed && today.isBefore(secondFriday)){
                Payslip.generatePayslip(e);
                e.ableToClaim = false;
                e.payClaimed = true;
            } else {
                System.out.println("Pay not claimed before 2nd Friday. Unable to claim pay this month");
                e.ableToClaim = false;
            }
        } else{
            System.out.println("Unable to claim pay");
        }
    }

    public static LocalDate getSecondFriday(YearMonth yearMonth) {
        // Get the first day of this month
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        // Find the first Friday of the month
        LocalDate firstFriday = firstDayOfMonth.with(DayOfWeek.FRIDAY);
        //Returns firstFriday + 7
        return firstFriday.plusDays(7);
    }
}
