<?php

/**
 * ReportingCloud PHP Wrapper
 *
 * Official wrapper (authored by Text Control GmbH, publisher of ReportingCloud) to access ReportingCloud in PHP.
 *
 * @link      http://www.reporting.cloud to learn more about ReportingCloud
 * @link      https://github.com/TextControl/txtextcontrol-reportingcloud-php for the canonical source repository
 * @license   https://raw.githubusercontent.com/TextControl/txtextcontrol-reportingcloud-php/master/LICENSE.md
 * @copyright © 2016 Text Control GmbH
 */
namespace TxTextControl\ReportingCloud\Filter;

use DateTime;
use DateTimeZone;
use TxTextControl\ReportingCloud\Validator\StaticValidator;

/**
 * DateTimeToTimestamp filter
 *
 * @package TxTextControl\ReportingCloud
 * @author  Jonathan Maron (@JonathanMaron)
 */
class DateTimeToTimestamp extends AbstractFilter
{
    /**
     * Convert the date/time format used by the backend (e.g. "2016-04-15T19:05:18+00:00") to a UNIX timestamp.
     *
     * @param string $dateTimeString Backend formatted date/time string
     *
     * @return int
     */
    public function filter($dateTimeString)
    {
        StaticValidator::execute($dateTimeString, 'DateTime');

        $dateTimeZone = new DateTimeZone($this->getTimeZone());
        $dateTime = DateTime::createFromFormat($this->getDateFormat(), $dateTimeString, $dateTimeZone);

        return $dateTime->getTimestamp();
    }
}
