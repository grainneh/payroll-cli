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
    Boolean payClaimed = false; //set for individual employees
    Boolean ableToClaim = true; //set for individual employees

    public void validateClaim(Employees e) {
        LocalDate today = LocalDate.now(); //day employee submits claim

        YearMonth thisMonth = YearMonth.now();
        LocalDate secondFriday = getSecondFriday(thisMonth);

        if(ableToClaim){ //Automatically true. Can be changed from firing etc.

            if(payClaimed){ //pay already claimed this month
                System.out.println("Pay already claimed");
            } if (!payClaimed && today.isBefore(secondFriday)){
                //pay employee
                ableToClaim = false;
                payClaimed = true;
            } else {
                System.out.println("Pay not claimed before 2nd Friday. Unable to claim pay this month");
                ableToClaim = false;
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
