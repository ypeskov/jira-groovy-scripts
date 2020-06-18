/*
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.MutableIssue
MutableIssue issue = ComponentAccessor.getIssueManager().getIssueObject("TEL-45")
*/

import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.MutableIssue
import java.lang.Math;
 
MutableIssue curIssue = issue

def hoursPerDay = 8
def target = "customfield_10501"
def srcStartDateField = "customfield_10500"

def cfManager = ComponentAccessor.getCustomFieldManager()
def cfTarget = cfManager.getCustomFieldObject(target)
def startDateField = cfManager.getCustomFieldObject(srcStartDateField)

def estimated = issue.getEstimate()
def logged = issue.getTimeSpent()

if (logged == null) {
  logged = 0
}

def period = estimated + logged

//get period in integer dates rounded to nearest up int
period = period/hoursPerDay/60/60
period = Math.ceil(((Double)period))

def startDate = issue.getCustomFieldValue(startDateField)

def date = startDate + (Integer)period

curIssue.setCustomFieldValue(cfTarget, date.toTimestamp())