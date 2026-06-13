
import java.util.*;

// CLASS: User
// Responsibilities:
//   - Stores user ID, name, role, and contact info
//   - Holds the user's access permissions
//   - Maintains the user's personal schedule

abstract class User {
    private String userId;
    private String name;
    private Role role;
    private AccessPermission permissions;
    private Schedule schedule;

    public String getUserId()               { return userId; }
    public Role getRole()                   { return role; }
    public AccessPermission getPermissions(){ return permissions; }
    public Schedule getSchedule()           { return schedule; }

    public boolean hasPermission(String action) { return false; }
}


// CLASS: Student (extends User)
// Responsibilities:
//   - Views room schedules
//   - Cannot create bookings

class Student extends User {
    public Schedule viewRoomSchedule(Room room) { return null; }
}


// CLASS: FacultyMember (extends User)
// Responsibilities:
//   - Requests room bookings for lectures and exams
//   - Reports equipment issues or incidents

class FacultyMember extends User {
    public BookingRequest requestBooking(Room room, Date date, int duration) { return null; }
    public IncidentReport reportIncident(String description, Room room)      { return null; }
}


// CLASS: AdministrativeStaff (extends User)
// Responsibilities:
//   - Requests room bookings for meetings and events
//   - Reports incidents

class AdministrativeStaff extends User {
    public BookingRequest requestBooking(Room room, Date date, int duration) { return null; }
    public IncidentReport reportIncident(String description, Room room)      { return null; }
}


// CLASS: FacilityManager (extends User)
// Responsibilities:
//   - Assigns maintenance staff to equipment or incidents
//   - Tracks and closes incident reports
//   - Generates system-wide reports

class FacilityManager extends User {
    public void assignMaintenance(IncidentReport report, MaintenanceStaff staff) {}
    public void closeIncident(IncidentReport report)                              {}
    public Report generateReport(String reportType)                               { return null; }
}


// CLASS: MaintenanceStaff (extends User)
// Responsibilities:
//   - Inspects and repairs equipment
//   - Updates equipment operational status after servicing
//   - Records maintenance history on equipment

class MaintenanceStaff extends User {
    public void performMaintenance(Equipment equipment, MaintenanceTask task) {}
    public void updateEquipmentStatus(Equipment equipment, String status)     {}
}


// CLASS: Role
// Responsibilities:
//   - Defines a named role (Student, Faculty, Admin, FacilityManager)
//   - Lists the default permissions attached to that role

class Role {
    private String roleName;
    private List<String> defaultPermissions;

    public String getRoleName()              { return roleName; }
    public List<String> getDefaultPermissions() { return defaultPermissions; }
}


// CLASS: AccessPermission
// Responsibilities:
//   - Stores allowed actions for a user
//   - Evaluates whether a user can access a room at a given time
//   - Handles special one-off permission grants

class AccessPermission {
    private List<String> allowedActions;
    private List<String> specialGrants;

    public boolean canAccess(Room room, Date time) { return false; }
    public void grantSpecialAccess(String action)  {}
}


// CLASS: Building
// Responsibilities:
//   - Stores building name, location, and ID
//   - Maintains the list of rooms it contains
//   - Provides lookup of rooms by type or availability

class Building {
    private String buildingId;
    private String name;
    private String location;
    private List<Room> rooms;

    public List<Room> getRooms()                          { return rooms; }
    public List<Room> getRoomsByType(String type)         { return null; }
    public Room findAvailableRoom(BookingRequest request) { return null; }
}


// CLASS: Room
// Responsibilities:
//   - Stores room ID, type, capacity, and usage rules
//   - Tracks the equipment available in the room
//   - Maintains the room's booking schedule
//   - Knows whether the room is restricted
//   - Checks its own availability for a given time slot

class Room {
    private String roomId;
    private String roomType;       // lecture hall, lab, meeting room, research lab
    private int seatingCapacity;
    private boolean isRestricted;
    private String usageRules;
    private List<Equipment> equipment;
    private Schedule schedule;

    public boolean isAvailable(Date start, Date end)        { return false; }
    public List<Equipment> getEquipment()                   { return equipment; }
    public Schedule getSchedule()                           { return schedule; }
    public boolean checkEquipmentAvailability(List<String> requiredEquipment) { return false; }
}


// CLASS: Equipment
// Responsibilities:
//   - Stores equipment ID, name, type, and location (room)
//   - Tracks operational status and usage limits
//   - Maintains full maintenance history
//   - Marks itself as unavailable while under maintenance

class Equipment {
    private String equipmentId;
    private String name;
    private String type;           // projector, computer, lab instrument, smart board
    private String status;         // operational, under_maintenance, faulty
    private int usageLimit;
    private int currentUsageCount;
    private List<MaintenanceTask> maintenanceHistory;

    public String getStatus()                            { return status; }
    public boolean isAvailableForUse()                   { return false; }
    public void markUnderMaintenance()                   {}
    public void markOperational()                        {}
    public void addMaintenanceRecord(MaintenanceTask task) {}
}


// CLASS: BookingRequest
// Responsibilities:
//   - Captures all details of a booking (room type, capacity, equipment, time)
//   - Holds its own approval status
//   - Links back to the requesting user

class BookingRequest {
    private String requestId;
    private User requestedBy;
    private String roomType;
    private int requiredCapacity;
    private List<String> requiredEquipment;
    private Date startTime;
    private Date endTime;
    private String purpose;        // lecture, exam, meeting, event
    private String status;         // pending, approved, rejected

    public String getStatus()    { return status; }
    public void approve()        { this.status = "approved"; }
    public void reject()         { this.status = "rejected"; }
}


// CLASS: BookingSystem
// Responsibilities:
//   - Validates a booking request against room availability and permissions
//   - Approves or rejects requests and adds approved ones to the room schedule
//   - Maintains the full registry of all booking requests

class BookingSystem {
    private List<BookingRequest> allRequests;
    private List<Building> buildings;

    public BookingRequest submitRequest(BookingRequest request)         { return null; }
    public boolean validateRequest(BookingRequest request)              { return false; }
    public Room findSuitableRoom(BookingRequest request)                { return null; }
    public void confirmBooking(BookingRequest request, Room room)       {}
    public List<BookingRequest> getRequestsByUser(User user)            { return null; }
}


// CLASS: Schedule
// Responsibilities:
//   - Holds a list of time-slotted bookings for a room or user
//   - Checks for conflicts in a given time range
//   - Adds and removes booking entries

class Schedule {
    private List<BookingRequest> bookings;

    public boolean hasConflict(Date start, Date end)    { return false; }
    public void addBooking(BookingRequest booking)       {}
    public void removeBooking(String requestId)         {}
    public List<BookingRequest> getBookings()           { return bookings; }
}


// CLASS: AccessControlSystem
// Responsibilities:
//   - Evaluates whether a user may enter a given room at a given time
//   - Logs every access attempt (granted or denied)
//   - Raises an incident for unauthorized access attempts

class AccessControlSystem {
    private List<AccessLog> accessLogs;

    public boolean requestAccess(User user, Room room, Date time) { return false; }
    public void logAccess(User user, Room room, boolean granted)  {}
    public List<AccessLog> getAccessLogs(Room room)               { return null; }
    public IncidentReport flagUnauthorizedAttempt(User user, Room room) { return null; }
}

// CLASS: AccessLog
// Responsibilities:
//   - Records a single access event (who, where, when, outcome)
//   - Supports security auditing and reporting

class AccessLog {
    private String logId;
    private User user;
    private Room room;
    private Date timestamp;
    private boolean accessGranted;

    public String getSummary() { return null; }
}


// CLASS: IncidentReport
// Responsibilities:
//   - Captures incident type, description, location, and priority
//   - Tracks current resolution status
//   - Links to the assigned facility manager and any resulting maintenance task

class IncidentReport {
    private String incidentId;
    private String type;           // broken equipment, power failure, unauthorized access
    private String description;
    private Room location;
    private User reportedBy;
    private String priority;       // low, medium, high, critical
    private String status;         // open, in_progress, resolved
    private FacilityManager assignedManager;
    private MaintenanceTask linkedTask;

    public void assignManager(FacilityManager manager) {}
    public void updateStatus(String newStatus)          {}
    public void close()                                 { this.status = "resolved"; }
}


// CLASS: MaintenanceTask
// Responsibilities:
//   - Describes the maintenance work to be done and its urgency
//   - Tracks assigned staff, start time, completion time, and outcome notes
//   - Keeps equipment locked from use until the task is completed

class MaintenanceTask {
    private String taskId;
    private Equipment targetEquipment;
    private MaintenanceStaff assignedStaff;
    private String description;
    private String urgency;
    private Date scheduledDate;
    private Date completedDate;
    private String outcome;
    private String status;         // scheduled, in_progress, completed

    public void markComplete(String outcomeNotes) {}
    public void assignStaff(MaintenanceStaff staff) {}
}


// CLASS: ReportGenerator
// Responsibilities:
//   - Aggregates data from rooms, equipment, bookings, and incidents
//   - Produces utilization, maintenance, and incident trend reports
//   - Supports management planning and budget decisions

class ReportGenerator {
    public Report generateRoomUtilizationReport(Date from, Date to)      { return null; }
    public Report generateEquipmentUsageReport(Date from, Date to)       { return null; }
    public Report generateMaintenanceFrequencyReport(Date from, Date to) { return null; }
    public Report generateIncidentTrendReport(Date from, Date to)        { return null; }
}


// CLASS: Report
// Responsibilities:
//   - Holds the generated report type, date range, and result data
//   - Formats and exports report content

class Report {
    private String reportType;
    private Date generatedAt;
    private Date fromDate;
    private Date toDate;
    private Map<String, Object> data;

    public String export(String format) { return null; } // e.g. PDF, CSV
    public Map<String, Object> getData() { return data; }
}